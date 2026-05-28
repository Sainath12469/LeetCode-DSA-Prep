class Solution {
    class Trie{
        Trie h[];
        int en,enn;
        Trie(){
            h=new Trie[26];
            en=enn=-1;
        }
    }
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int i,j;
        int res[]=new int[wordsQuery.length];
        Trie t=new Trie();
        int z=0;
        for(i=0;i<wordsContainer.length;i++){
            Trie temp=t;
            for(j=wordsContainer[i].length()-1;j>=0;j--){
                int ch=wordsContainer[i].charAt(j)-'a';
                if(temp.h[ch]==null){
                    temp.h[ch]=new Trie();
                }
                temp=temp.h[ch];
                if(temp.en==-1||temp.en>j){
                    temp.enn=i;
                    temp.en=j;
                }
            }
            if(wordsContainer[i].length()<wordsContainer[z].length())
            z=i;
        }
        for(i=0;i<wordsQuery.length;i++){
            Trie temp=t;
            int r=-1;
            for(j=wordsQuery[i].length()-1;j>=0;j--){
                int ch=wordsQuery[i].charAt(j)-'a';
                if(temp.h[ch]==null){
                    r=temp.enn;
                    break;
                }
                temp=temp.h[ch];
                if(j==0){
                    r=temp.enn;
                }
            }
            res[i]=r==-1?z:r;
        }
        return res;
    }
}