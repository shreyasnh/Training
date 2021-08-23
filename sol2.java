import java.util.ArrayList;
import java.util.List;
public class Words {


    final static int SIZE = 26;


    static class Nodes
    {
        Nodes[] children = new Nodes[SIZE];
        boolean isLeaf;
        public Nodes() {
            isLeaf = false;
            for (int i =0 ; i< SIZE ; i++)
                    children[i] = null;
        }
    }     
    static Nodes root;
    static void insert(Nodes root, String Key)
    {
        int n = Key.length();
        Nodes specific = root;

        for (int i=0; i<n; i++)
        {
            int index = Key.charAt(i) - 'a';

            if (specific.children[index] == null)
                specific.children[index] = new Nodes();

            specific = specific.children[index];
        }

        specific.isLeaf = true;
    }
    static List<Integer> findPrefix(Nodes root, String key)
    {
        List<Integer> prefixPositions = new ArrayList<Integer>();
        int level;
        Nodes specific = root;

        for (level = 0; level < key.length(); level++)
        {
            int index = key.charAt(level) - 'a';
            if (specific.isLeaf == true)
                prefixPositions.add(level);
            if (specific.children[index] == null)
                return prefixPositions;

            specific = specific.children[index];
        }
        if (specific != null && specific.isLeaf)
            prefixPositions.add(level);

        return prefixPositions;
    }

    static boolean isPossible(Nodes root, String word)
    {
        List<Integer> prefixPositions1 = findPrefix(root, word);
        if (prefixPositions1.isEmpty())
            return false;
        for (Integer len1 : prefixPositions1) {
            String restOfSubstring = word.substring(len1, word.length());
            List<Integer> prefixPositions2 = findPrefix(root, restOfSubstring);
            for (Integer len2 : prefixPositions2) {
                if (len1 + len2 == word.length())
                    return true;
            }
        }

        return false;
    }

    public static void main(String args[])
    {

        String[] dictionary = {"news", "newspa", "paper", "god"};

        String word = "newspaper";
        root = new Nodes();
        for (int i=0; i<dictionary.length; i++)
            insert(root, dictionary[i]);

        if(isPossible(root, word))
            System.out.println( "Correct match");
        else
            System.out.println("No Match");
    }
}
