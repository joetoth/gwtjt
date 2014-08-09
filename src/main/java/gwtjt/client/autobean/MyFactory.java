package gwtjt.client.autobean;

import gwtjt.shared.ISuperModel;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanFactory.Category;

@Category(Magic.class)
public interface MyFactory extends AutoBeanFactory {
  AutoBean<ISuperModel> superModel();
}