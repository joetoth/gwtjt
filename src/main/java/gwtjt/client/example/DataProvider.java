package gwtjt.client.example;

import java.util.List;

public interface DataProvider<T> {
  List<T> getData(T parent, int page);
  String getName(T object);
}
