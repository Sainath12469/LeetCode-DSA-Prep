class Solution {
    public int numberOfSpecialChars(String word) {
        int i,j;
        int c=0;
        for(i=0;i<26;i++){
            boolean f1=false,f2=false;
            for(j=0;j<word.length();j++){
                if(word.charAt(j)-'a'==i){
                    f1=true;
                    if(f2)
                    break;
                }
                else if(word.charAt(j)-'A'==i)
                f2=true;
            }
            if(f1&&f2&&j==word.length())
            c++;
        }
        return c;
    }
}