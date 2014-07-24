package my.client;

import java.util.Collection;
import java.util.Iterator;

public class ObservableCollection implements Collection {

  Collection wrapped;

  private ObservableCollection(Collection<?> collection) {

  }


  public <T> T observe(T t) {
    if (t instanceof Collection) {

    }
    return t;

  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return wrapped.size();
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return wrapped.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    // TODO Auto-generated method stub
    return wrapped.contains(o);
  }

  @Override
  public Iterator iterator() {
    // TODO Auto-generated method stub
    return wrapped.iterator();
  }

  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    return wrapped.toArray();
  }

  @Override
  public Object[] toArray(Object[] a) {
    // TODO Auto-generated method stub
    return wrapped.toArray(a);
  }

  @Override
  public boolean add(Object e) {
    // TODO Auto-generated method stub
    return wrapped.add(e);
  }

  @Override
  public boolean remove(Object o) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean containsAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean retainAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

}
