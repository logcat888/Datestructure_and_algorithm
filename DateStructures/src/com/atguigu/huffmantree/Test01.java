package com.atguigu.huffmantree;


import java.io.*;
import java.util.*;

/**
 * @author chenhuiup
 * @create 2020-10-03 18:49
 */
/*
利用赫夫曼树进行编码
1. 构建赫夫曼树：利用字符串中字符出现的次数，以次数为权值构建赫夫曼树，节点包括4个出现，次数、字节、左节点、右节点。
2. 根据赫夫曼树获取编码表：根据叶子节点到根节点的路径，令左节点为0，右节点为1，得到字符对应的前缀编码。
    所谓前缀编码就是任何一个编码不会是另一个编码的前缀，不会出现匹配的多义性。
3. 根据编码表将字符串编码成二进制的字符串；
4. 将二进制的字符串，每次取8位转换为一个字节，调用Integer的 Integer.parseInt(二进制, 2);
5. 得到编码后的字节数组。
利用赫夫曼编码表进行解码：
1. 需要根据赫夫曼编码表对编码后的字节数组解码
2. 将编码后的字节转换为二进制的补码字符串，Integer.toBinaryString（字节），对于最后一个字节不需要进行补高位操作。
    对于正数需要进行补高位操作，因为正数得到的二进制没有高位，比如1就是1,2就是10，需要补成8位数。 但是最后一个
    字节不需要，因为将二进制转换为字节时，可能不是完整的8位二进制，所以不需要进行额外的补高位。
3. 将二进制补码字符串和编码表对应取出对应的字符。

利用赫夫曼编码进行对文件的压缩和解压
1. 利用文件输入流，将文件读取到字节数组，对字节数组进行编码压缩。
2. 利用对象输出流ObjectOutputStream的writeObject将 压缩后的字节数组和 编码表当做两个对象写出，使用对象流在读取文件时就是一个个的对象。
3. 利用对象输入流ObjectInputStream的readObject读取文件，分别获取压缩后的字节数组和编码表，对其进行解压。
4. 将解压后的字节数组利用FileOutputStream输出。
 */
public class Test01 {
    public static void main(String[] args) {
        String line = "i like like like java do you like a java";
        System.out.println(codeString(line));
        System.out.println(decodeString(line, codeString(line)));
        System.out.println(zip(line).length);
        System.out.println("huffmanZip:" + Arrays.toString(zip(line)));
        byte[] decode = decode(codeByte(line), zip(line));
        System.out.println("解码后：" + new String(decode));

        // 测试压缩图片文件
//        String srcPath = new String("G:\\aaa\\src.jpg");
        String destPath = new String("G:\\aaa\\dest.zip");
//        zipFile(srcPath,destPath);

        // 测试压缩文件的解压
        String destUnzipPath = new String("G:\\aaa\\unzip.jpg");
        unZip(destPath,destUnzipPath);

    }

    /**
     * //编写一个方法，完成对压缩文件的解压
     * @param srcFile 压缩文件路径
     * @param destFile 解压路径
     */
    public static void unZip(String srcFile, String destFile){
        FileInputStream fis=null;
        ObjectInputStream ois= null;
        FileOutputStream fos =null;
        try {
            // 1. 创建输入输出流
            fis = new FileInputStream(srcFile);
            // 创建对象输入流
            ois = new ObjectInputStream(fis);
            // 创建文件输出流
            fos = new FileOutputStream(destFile);
            // 2. 流的操作
            //  读取byte数组，Huffman编码后的字节数组
            byte[] b =  (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte,String> codeTable = (HashMap<Byte,String>)ois.readObject();
            System.out.println(codeTable);
            // 解码
            byte[] decode = decode(codeTable, b);
            //将 decode数组写出到目标文件
            fos.write(decode);
            // 3. 关闭资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 编写方法，将一个文件进行压缩
    public static void zipFile(String srcFile, String destFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // 1.创建流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(fos);
            // 2. 流的操作
            byte[] b = new byte[fis.available()];
            // 2.1 读取文件
            fis.read(b);
            // 压缩文件
            byte[] zip = zip(new String(b));
            // 2.2 文件写出
            // 2.2.1 把赫夫曼编码后的字节数组写入压缩文件
            // 这里我们以对象流的方式写入赫夫曼编码是为了以后我们恢复源文件时使用
            oos.writeObject(zip);
            // 2.2.2 把赫夫曼编码表写入压缩文件
            oos.writeObject(codeByte(new String(b)));

        } catch (Exception e) {
            e.printStackTrace();
            // 流的关闭
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // 完成数据的解压
    // 思路
    // 1. huffmanCodeByte[]:[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //  重新转成 赫夫曼编码对应的二进制的字符串“101010001011111111001000101111111100100010111111110010010100110111”
    // 2. 赫夫曼编码对应的二进制的字符串“10101000101111111100” =》对照赫夫曼编码表转换成i like like like java do you like a java

    /**
     * 编写一个方法，完成对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼解码表map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. 先得到HuffmanBytes 对应的二进制的字符串，形式101010001011...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = !(i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(flag, huffmanBytes[i]));
        }
        //创建解码表
        Set<Map.Entry<Byte, String>> entries = huffmanCodes.entrySet();
        HashMap<String, Byte> decodeTable = new HashMap<>();
        for (Map.Entry<Byte, String> entry : entries) {
            decodeTable.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();
        // i 可以理解成就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;// 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 递增取出key
                String key = stringBuilder.substring(i, i + count);
                if ((b = decodeTable.get(key)) == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count; // i 直接移动count
        }
        //返回byte数组 =》 “ i like like like java do you like a java”
        byte[] c = new byte[list.size()];
        for (int i = 0; i < c.length; i++) {
            c[i] = list.get(i);
        }
        return c;
    }


    /**
     * 将一个byte 转成一个二进制的字符串
     *
     * @param flag 传入的byte
     * @param b    flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false，表示不需要补高位。如果是最后一个字节无需补高位
     *             ，因为有可能按照赫夫曼编码后得到的字节字符串不够整除8，最后的一个字节不够8位，所以转换时也无需按照8位得到二进制字符串。
     * @return 是该b 对应的二进制的字符串（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存 b
        int temp = b;// 将b 转成 int
        //如果是正数我们还存在补高位，正数 1 转换成二进制补码的字符串是 1，而没有高位
        if (flag) {
            temp |= 256; //按位与 256  1 0000 0000 | 0000 0001 =》 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }


    }

    // 解码：根据赫夫曼编码表解码
    public static String decodeString(String rawData, String codeData) {
        HashMap<Character, String> codeTable = code(rawData);
        Set<Map.Entry<Character, String>> entries = codeTable.entrySet();
        //创建解码表
        HashMap<String, Character> decodeTable = new HashMap<>();
        for (Map.Entry<Character, String> entry : entries) {
            decodeTable.put(entry.getValue(), entry.getKey());
        }
        StringBuilder decodeBuilder = new StringBuilder();
        int length = 1;
        while (true) {
            if (codeData.length() <= 0) {
                break;
            }
            String key = codeData.substring(0, length);
            if (decodeTable.get(key) != null) {
                decodeBuilder.append(decodeTable.get(key));
                codeData = codeData.substring(length);
                length = 1;
            }
            length++;
        }
        return decodeBuilder.toString();
    }

    // 将赫夫曼编码对应的字符串转换为字符数组
    public static byte[] zip(String line) {
        String codeString = codeString(line);
        // 赫夫曼编码数组的长度
        int length = (codeString.length() + 7) / 8;
        byte[] huffmanCodeBytes = new byte[length];

        int index = 0;
        for (int i = 0; i < codeString.length(); i += 8) {
            String data;
            if (i + 8 > codeString.length()) {
                data = codeString.substring(i);
            } else {
                data = codeString.substring(i, i + 8);
            }
            // 将 10000110 =》转化为字节  parseInt第二个参数是指定为二进制
            huffmanCodeBytes[index] = (byte) Integer.parseInt(data, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //计算字符串对应的赫夫曼编码
    public static String codeString(String line) {
        StringBuilder builder = new StringBuilder();
        HashMap<Character, String> map = code(line);
        char[] chars = line.toCharArray();
        for (char c : chars) {
            String data = map.get(c);
            builder.append(data);
        }
        return builder.toString();
    }

    //赫夫曼字节编码表
    public static HashMap<Byte, String> codeByte(String line) {
        HashMap<Character, String> codeTable = code(line);
        HashMap<Byte, String> map = new HashMap<Byte, String>();
        for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
            map.put((byte) (entry.getKey().charValue()), entry.getValue());
        }
        return map;
    }


    // 计算每个字符对应的编码表,在赫夫曼树的路径 ,规定向左遍历为0，向右遍历为1
    public static HashMap<Character, String> code(String line) {
        Node1 huffmanTree = createHuffmanTree(line);
        HashMap<Character, String> map = new HashMap<>();
        getcode(huffmanTree, "", builder);
        return wplMap;
    }

    static HashMap<Character, String> wplMap = new HashMap<>();
    static StringBuilder builder = new StringBuilder();

    public static void getcode(Node1 huffmanTree, String flag, StringBuilder builder) {
        StringBuilder builder2 = new StringBuilder(builder);
        builder2.append(flag);
        if (huffmanTree.c == null) {
            //向左遍历
            getcode(huffmanTree.left, "0", builder2);
            //向右遍历
            getcode(huffmanTree.right, "1", builder2);
        } else {
            wplMap.put(huffmanTree.c, builder2.toString());
        }
    }


    // 构建赫夫曼树
    public static Node1 createHuffmanTree(String line) {
        List<Node1> list = toList(line);

        while (list.size() > 1) {
            //排序
            Collections.sort(list);

            //取list集合前2个元素
            Node1 left = list.get(0);
            Node1 right = list.get(1);
            //创建父节点
            Node1 parent = new Node1(left.value + right.value, null);

            parent.left = left;
            parent.right = right;

            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        return list.get(0);
    }

    //统计字符串中各个字符出现的次数
    public static HashMap<Character, Integer> countChar(String line) {
        char[] chars = line.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : chars) {
            int value = map.getOrDefault(c, 0) + 1;
            map.put(c, value);
        }
        return map;
    }

    // 将字符及出现次数包装为节点的ArrayList中
    public static List<Node1> toList(String line) {
        HashMap map = countChar(line);
        ArrayList<Node1> list = new ArrayList<>();
        Set<Character> set = map.keySet();
        Iterator<Character> iterator = set.iterator();
        while (iterator.hasNext()) {
            Character key = iterator.next();
            Object value = map.get(key);
            list.add(new Node1((Integer) value, key));
        }
        return list;
    }


}

//创建节点类
class Node1 implements Comparable<Node1> {
    int value; //字符出现次数
    Character c; //字符
    Node1 left; //左子节点
    Node1 right; //右子节点

    public Node1(int value, Character c) {
        this.value = value;
        this.c = c;
    }

    public Node1(int value) {
        this.value = value;
    }


    @Override
    public int compareTo(Node1 o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "[value=" + value + ", c=" + c + ']';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node1 node1 = (Node1) o;
        return value == node1.value &&
                c == node1.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, c);
    }
}