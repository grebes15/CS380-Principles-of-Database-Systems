import java.io.*;

import java.util.*;

/**
 * Class that builds a random tree.  Each node stores a random 
 * name from a Sequence of names.
 *
 * @author Lucy Perry (lep)
 * @version JDSL 2
*/
public class RandomTreeBuilder { 

    /* ************************************ */ 
    /* The members described in the lesson. */
    /* ************************************ */ 

    //b6.1
    protected static Tree tree;
    protected static ArrayList names;
    //e6.1
    
    /**
     *Builds a random tree.  The build method does the work.
     */
    //b6.2
    public static Tree randomTree(int n) {
        // Create a random binary tree with n external nodes
        tree = new GeneralTree();
        names = NameGenerator.getNames();
        build(tree.getRoot(), n);  // auxiliary recursive method
        return tree;
    }
    //e6.2
    
    /**
     * Recursively build a random tree.  The algorithm builds a subtree with
     * n nodes with root node p as follows:
     *   - If n=1 then do nothing
     *   - Otherwise
     *       - randomly select the size of the subtree rooted at the first child
     *       - repeat until the number of nodes to build has been distributed to 
     *         children subtrees.
     *       - randomly permute the order of sizes.
     *       - recursively build each subtree
     */
    //b6.3 
    protected static void build(Position p, int n) {
        int toBuild = n-1;

        ArrayList sizes = new ArrayList();

        if (tree.isExternal(p)) 
	    {
		while (toBuild > 0) 
		    {
			int size = randomInteger(1, toBuild);
			sizes.add(new Integer(size));
			toBuild-=size;
		    }
            
		//permute(sizes);
		Collections.shuffle(sizes);

		for(int i = 0; i < sizes.size(); i++)
		    {
			
			Position pos = tree.addPosition(null, p);
			int size = 
			    ((Integer)sizes.get(i)).
			    intValue();
			
			build(pos,size);
		
		    }

		tree.replaceElement(p,pickName());
        
	    }
    
    }
    //e6.3

    /* ************************************ */ 
    /* Members not described in the lesson. */
    /* ************************************ */ 
    
    protected static Random gen = new Random();

    /**
     * Randomly pick a name. 
     */
    protected static String pickName() 
    {
    
	//int i=randomInteger(0,names.size()-1);
	//System.out.println("Names.size(): " + 
	//		   names.size());

	if( names.size() > 0 )
	    {

		int i = gen.nextInt(names.size()-1);
		return (String)names.get(i);
    
	    }

	else
	    {

		return "Final";

	    }

    }

    /** 
     * Return a random integer i such that min <= i <= max
     */
    protected static int randomInteger(int min, int max) 
    {

	//System.out.println("min: " + min);
	//System.out.println("max: " + max);

	int r = gen.nextInt(max-min+1);
        return r+min;
    
    }

}
