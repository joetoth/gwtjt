package my.client.beans;

public interface MutableObservable<E extends Enum<E>> extends Mutable<E>,
    ObservablePropertyChanges {

}
