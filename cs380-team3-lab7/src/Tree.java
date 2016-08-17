import java.util.Iterator;

public interface Tree 
{

    public boolean isRoot(Position p);

    public Position parent(Position p);

    public Position getRoot();

    public Position addPosition(Object newChildObject, 
				Position newPositionParent);
    
    public boolean isExternal(Position p);

    public boolean isInternal(Position p);

    public void replaceElement(Position p, Object element);

    public Iterator children(Position p);

}
