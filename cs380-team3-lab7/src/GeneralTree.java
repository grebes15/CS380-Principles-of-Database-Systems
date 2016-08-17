import java.util.Iterator;

public class GeneralTree implements Tree
{

    private GTNode root;

    public GeneralTree()
    {

	root = new GTNode();

    }

    public boolean isRoot(Position p)
    {

	if( root == p )
	    {

		return true;

	    }

	return false;

    }

    public Position getRoot()
    {

	return root;

    }

    public Position parent(Position p)
    {

	return ((GTNode)p).getParent();
	
    }

    public Position addPosition(Object newChildObject, 
			    Position newPositionParent)
    {
	
	GTNode newPositionChild = 
	    new GTNode(newPositionParent, newChildObject);

	((GTNode)newPositionParent).addChild(newPositionChild);

	return newPositionChild;

    }

    public boolean isExternal(Position p)
    {

	GTNode gtNode = (GTNode)p;
	if( gtNode.getChildren().size() == 0 )
	    {

		return true;

	    }

	return false;

    }

    public boolean isInternal(Position p)
    {

	GTNode gtNode = (GTNode)p;
	if( gtNode.getChildren().size() == 0 )
	    {

		return false;

	    }

	return true;

    }

    public void replaceElement(Position p, Object element)
    {

	GTNode gtNode = (GTNode)p;
	gtNode.setElement(element);

    }

    public Iterator children(Position p)
    {

	GTNode gtNode = (GTNode)p;
	return gtNode.getChildren().iterator();

    }

}
