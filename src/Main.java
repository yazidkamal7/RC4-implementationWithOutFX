public class Main {

    private static int [] s = new int[256];
    private static int [] t = new int[256];

    public static void main(String[] args) {
        StringBuilder out = new StringBuilder();
        String plainText = "TEST TeXt";
        String key = "Yazid";
        KSA(key);
        String genKey = PRGA(plainText.length());
        System.out.println("Key = " + genKey);
        plainText = txtToBinnary(plainText);
        genKey = txtToBinnary(genKey);
        int [] arrplain = strToInt(plainText);
        int [] arrkey = strToInt(genKey);

        for (int i = 0; i < arrplain.length; i++) {
            int xor = arrplain[i] ^ arrkey[i];
            out.append(xor);

        }
        String output = out.toString();
        output = binnaryToTxt(output);
        System.out.println(output);

    }



    public static void KSA(String key){
        int [] arrkey = strToInt(key);
        int j=0;
        for (int i = 0; i < 256; i++) {
            s[i]=i;
            t[i]=arrkey[i%key.length()];
        }
        for (int i = 0; i < 256; i++) {
            j = (j+s[i]+t[i])%256;
            swap(i,j);
        }
        print();

    }
    public static String PRGA(int lenght){
        int i=0,j=0,key;
        StringBuilder Key = new StringBuilder();
        for (int k = 0; k < lenght; k++) {
            i = (i+1)%256;
            j = (j+s[i])%256;
            swap(i,j);
            key = s[(s[i]+s[j])%256];
            Key.append(key);
            
        }
        print();
        return Key.toString();

    }

    private static void swap(int i, int j) {
        int temp = s[i];
        s[i]=s[j];
        s[j]=temp;
    }

    public static int[] strToInt(String input){
        int [] toInt = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            toInt[i] = (int) input.charAt(i);
        }
        return  toInt;
    }
    public static void print(){
        System.out.println("**********S array***********");
        for (int i = 0; i < 256; i++) {
            System.out.print(s[i]+", ");
        }
        System.out.println();

    }
    public static String txtToBinnary(String input){
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int charACII = (int)input.charAt(i);
            for (int j = 0; j < (8 - Integer.toBinaryString(charACII).length()); j++) {
                st.append("0");
            }
            st.append(Integer.toBinaryString(charACII));

        }
        return st.toString();
    }
    public static String binnaryToTxt(String input){
        StringBuilder plain = new StringBuilder();
        String c;
        for (int i = 0; i < input.length(); i+=8) {
            c=input.substring(i,i+8);
            char ch = (char) Integer.parseInt(c,2);
            plain.append(ch);
        }
        return plain.toString();
    }
}
