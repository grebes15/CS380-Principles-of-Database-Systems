import java.util.ArrayList;
import java.io.Serializable;

public class GTNode implements Position, Serializable
{

    private Position parent;

    private Object dataElement;

    private ArrayList childrenList;

    public GTNode()
    {

	parent = null;
	dataElement = null;
	childrenList = new ArrayList();

    }

    public GTNode(Position parent, Object element)
    {

	this.parent = parent;
	dataElement = element;
	childrenList = new ArrayList();

    }

    public void setParent(Position p)
    {

	parent = p;

    }

    public Position getParent()
    {

	return parent;

    }

    public Object element()
    {

	return dataElement;

    }

    public void setElement(Object element)
    {

	dataElement = element;

    }

    public void addChild(Position newChild)
    {

	childrenList.add(newChild);

    }

    public ArrayList getChildren()
    {

	return childrenList;

    }

}
