package my.client;

import my.shared.ISuperModel;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanFactory.Category;

@Category(Magic.class)
interface MyFactory extends AutoBeanFactory {
  AutoBean<ISuperModel> superModel();
}