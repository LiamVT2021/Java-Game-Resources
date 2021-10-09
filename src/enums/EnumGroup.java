package enums;

public interface EnumGroup<E> {

    public GroupID<E> id();

    public default E first(){
        return id().first();
    }

    public default int size(){
        return id().size();
    }

    // public default E[] getMembers(E[] all){
    //     E[] mem = new E[size()];
    // }
    
}
