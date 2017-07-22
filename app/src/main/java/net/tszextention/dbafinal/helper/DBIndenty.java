package net.tszextention.dbafinal.helper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBIndenty{
	/**
	 * @title 属性
	 * @description 【非主键】的【基本数据类型】 都是属性
	 * @author michael Young (www.YangFuhai.com)
	 * @version 1.0
	 * @created 2012-10-10
	 */
	public static class Property {
		private String fieldName;
		private Class<?> dataType;
		private Field field;
		
		private Method get;
		private Method set;
		public Property () {
			this(null);
		}
		public Property (Field field) {
			this(null, field);
		}
		public Property (Class<?> clazz,Field field) {
			if (field!=null) {
				setFieldName(field.getName());
				if (clazz!=null) {
					setSet(getFieldSetMethod(clazz, field));
					setGet(getFieldGetMethod(clazz, field));
				}
				setDataType(field.getType());
				setField(field);
			}
		}
		public void setValue(Object receiver , Object value){
			if(set!=null && value!=null){
				try {
					if (dataType == String.class) {
						set.invoke(receiver, value.toString());
					} else if (dataType == int.class || dataType == Integer.class) {
						set.invoke(receiver, value == null ? (Integer) null : Integer.parseInt(value.toString()));
					} else if (dataType == float.class || dataType == Float.class) {
						set.invoke(receiver, value == null ? (Float) null: Float.parseFloat(value.toString()));
					} else if (dataType == double.class || dataType == Double.class) {
						set.invoke(receiver, value == null ? (Double) null: Double.parseDouble(value.toString()));
					} else if (dataType == long.class || dataType == Long.class) {
						set.invoke(receiver, value == null ? (Long) null: Long.parseLong(value.toString()));
					} else if (dataType == java.util.Date.class || dataType == java.sql.Date.class) {
						//set.invoke(receiver, value == null ? (Date) null: FieldUtils.stringToDateTime(value.toString()));
					} else if (dataType == boolean.class || dataType == Boolean.class) {
						boolean flag="true".equals(value.toString());
						boolean flag2="1".equals(value.toString());
						set.invoke(receiver, value == null ? (Boolean) null: (flag||flag2));
					} else {
						set.invoke(receiver, value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					String name=field.getName();
					//java 虚拟机 Dalvik 中Object 对象没有shadow$_klass_和shadow$_monitor_ 两个默认属性
					//android(java 虚拟机) ART 中Object 对象有shadow$_klass_和shadow$_monitor_ 两个默认属性，无法通过反射设置
					if (!"shadow$_klass_".equals(name)&&!"shadow$_monitor_".equals(name)) {
						field.setAccessible(true);
						field.set(receiver, value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * 获取某个实体执行某个方法的结果
		 * @param obj
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public <T> T getValue(Object obj){
			if(obj != null && get != null) {
				try {
					return (T)get.invoke(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public Class<?> getDataType() {
			return dataType;
		}
		public void setDataType(Class<?> dataType) {
			this.dataType = dataType;
		}
		public Method getGet() {
			return get;
		}
		public void setGet(Method get) {
			this.get = get;
		}
		public Method getSet() {
			return set;
		}
		public void setSet(Method set) {
			this.set = set;
		}

		public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}
	}
	///////////////////////////////////////
	private static Method getFieldGetMethod(Class<?> clazz, Field f) {
		String fn = f.getName();
		Method m = null;
		if(f.getType() == boolean.class){
			m = getBooleanFieldGetMethod(clazz, fn);
		}
		if(m == null ){
			m = getFieldGetMethod(clazz, fn);
		}
		return m;
	}
	private static Method getBooleanFieldGetMethod(Class<?> clazz, String fieldName) {
		try {
			String mn = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			if(isISStart(fieldName)){
				mn = fieldName;
			}
			return clazz.getDeclaredMethod(mn);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static Method getFieldSetMethod(Class<?> clazz, Field f) {
		try {
			String fieldName = f.getName();
			if ("serialVersionUID".equals(fieldName)||"shadow$_klass_".equals(fieldName)||"shadow$_monitor_".equals(fieldName)) {
				return null;
			}
			String mn = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			if (Character.isUpperCase(fieldName.charAt(1))){
				mn = "set"+fieldName;
			}
			Method method=clazz.getDeclaredMethod(mn, f.getType());
			return method;
		} catch (NoSuchMethodException e) {
			if(f.getType() == boolean.class){
				return getBooleanFieldSetMethod(clazz, f);
			}
		}
		return null;
	}
	private static Method getBooleanFieldSetMethod(Class<?> clazz, Field f) {
		try {
			String fn = f.getName();
			String mn = "set" + fn.substring(0, 1).toUpperCase() + fn.substring(1);
			if (Character.isUpperCase(fn.charAt(1))){
				mn = "set"+fn;
			}
			if(isISStart(f.getName())){
				mn = "set" + fn.substring(2, 3).toUpperCase() + fn.substring(3);
			}
			return clazz.getDeclaredMethod(mn, f.getType());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static boolean isISStart(String fieldName){
		if(fieldName==null || fieldName.trim().length()==0)
			return false;
		//is开头，并且is之后第一个字母是大写 比如 isAdmin
		return fieldName.startsWith("is") && !Character.isLowerCase(fieldName.charAt(2));
	}
	private static Method getFieldSetMethod(Class<?> clazz, String fieldName) {
		try {
			return getFieldSetMethod(clazz, clazz.getDeclaredField(fieldName));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static Method getFieldGetMethod(Class<?> clazz, String fieldName) {
		try {
			if ("serialVersionUID".equals(fieldName)||"shadow$_klass_".equals(fieldName)||"shadow$_monitor_".equals(fieldName)) {
				return null;
			}
			String mn = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			if (Character.isUpperCase(fieldName.charAt(1))){
				mn = "get"+fieldName;
			}
			return clazz.getDeclaredMethod(mn);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}
	/////////////////////////////////////////////////////
	private static ArrayList<Property> getAllFilds(Class<?> clazz) {
		ArrayList<Property> fieldArray = new ArrayList<Property>();
		Class superClass = clazz.getSuperclass();
		if (superClass!=null&&superClass!=Serializable.class) {
			ArrayList<Property> fieldsTmp=getAllFilds(superClass);
			if (fieldsTmp!=null&&fieldsTmp.size()>0){
				fieldArray.addAll(fieldsTmp);
			}
		}
		Field[] fields2=clazz.getDeclaredFields();
		if (fields2!=null&&fields2.length>0){
			for (int i=0;i<fields2.length;i++){
				Field field =  fields2[i];
				Property property = new Property(clazz,field);
				fieldArray.add(property);
			}
		}
		return fieldArray;
	}

	public static boolean setValue( Object table,String name,Object value){
		boolean flag=false;
		try{
			ArrayList<Property> fieldArray = getAllFilds(table.getClass());
			if (fieldArray!=null&&fieldArray.size()>0) {
				Property findproperty = null;
				for (Property property : fieldArray) {
					if (name.equals(property.getFieldName())) {
						findproperty = property;
						break;
					}
				}
				if (findproperty != null) {
					if (!"serialVersionUID".equals(findproperty.fieldName)) {
						findproperty.setValue(table, value);
					}
				}
			}
			flag=true;
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	public static Object getValue( Object table,String name) {
		Object value=null;
		try {
			ArrayList<Property> fieldArray = getAllFilds(table.getClass());
			if (fieldArray!=null&&fieldArray.size()>0) {
				for (Property property : fieldArray) {
					if (property.getFieldName().equals(name)){
						value=property.getValue(table);
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return value;
	}
//	public boolean setValue(String name,Object value){
//		boolean flag=false;
//		ArrayList<Property> fieldArray = getAllFilds(this.getClass());
//		if (fieldArray!=null&&fieldArray.size()>0) {
//			Property findproperty=null;
//			for (Property property : fieldArray) {
//				if (name.equals(property.getFieldName())){
//					findproperty = property;
//					break;
//				}
//			}
//			if (findproperty!=null) {
//				if (!"serialVersionUID".equals(findproperty.fieldName)) {
//					findproperty.setValue(this, value);
//				}
//			}
//		}
//		return flag;
//	}
//	public Object getValue(String name) {
//		Object value=null;
//		try {
//			ArrayList<Property> fieldArray = getAllFilds(this.getClass());
//			if (fieldArray!=null&&fieldArray.size()>0) {
//				for (Property property : fieldArray) {
//					if (property.getFieldName().equals(name)){
//						value=property.getValue(this);
//						break;
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return value;
//	}
	public static <T> T copy( T table, T fromObj){
		try {
			if (fromObj!=null){
				ArrayList<Property> fieldArray = getAllFilds(fromObj.getClass());
				ArrayList<Property> fieldArray2 = getAllFilds(table.getClass());
				if (fieldArray!=null&&fieldArray.size()>0) {
					for (Property property : fieldArray) {
						for (Property property2 :fieldArray2) {
							if (property.getFieldName().equals(property2.getFieldName())){
								if (!"serialVersionUID".equals(property2.getFieldName())) {
									property2.setValue(table, property.getValue(fromObj));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return table;
	}
	//	public void copy(DBIndenty fromObj){
//		try {
//			if (fromObj!=null){
//				ArrayList<Property> fieldArray = getAllFilds(fromObj.getClass());
//				ArrayList<Property> fieldArray2 = getAllFilds(this.getClass());
//				if (fieldArray!=null&&fieldArray.size()>0) {
//					for (Property property : fieldArray) {
//						for (Property property2 :fieldArray2) {
//							if (property.getFieldName().equals(property2.getFieldName())){
//								if (!"serialVersionUID".equals(property2.getFieldName())) {
//									property2.setValue(this, property.getValue(fromObj));
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	//not test 
	public static <T> void setValuesFromMap( T table,Map<String, ?> map) {
		try {
			if (map!=null){
				ArrayList<Property> fieldArray = getAllFilds(table.getClass());
				if (fieldArray!=null&&fieldArray.size()>0) {
					for (Property property : fieldArray) {
						for (String key : map.keySet()) {
							if (property.getFieldName().equals(key)){
								if (!"serialVersionUID".equals(key)) {
									property.setValue(table, map.get(key));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//是否与上面功能重复？
//	public static <T> void setValuesFromStringMap( T table,Map<String, String> map) {
//		try {
//			if (map!=null){
//				ArrayList<Property> fieldArray = getAllFilds(table.getClass());
//				if (fieldArray!=null&&fieldArray.size()>0) {
//					for (Property property : fieldArray) {
//						for (String key : map.keySet()) {
//							if (property.getFieldName().equals(key)){
//								if (!"serialVersionUID".equals(key)) {
//									property.setValue(table, map.get(key));
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	///////////////////////
	public static Map<String, Object> toMap(Object obj) {
		Map<String, Object> dMap = new HashMap<String, Object>();
		try {
			ArrayList<Property> fieldArray = getAllFilds(obj.getClass());
			if (fieldArray!=null&&fieldArray.size()>0) {
				for (Property property : fieldArray) {
					Object value=property.getValue(obj);
					dMap.put(property.getFieldName(), value);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dMap;
	}
	public static Map<String, String> toStringMap(Object obj) {
		Map<String, String> dMap = new HashMap<String, String>();
		try {
			ArrayList<Property> fieldArray = getAllFilds(obj.getClass());
			if (fieldArray!=null&&fieldArray.size()>0) {
				for (Property property : fieldArray) {
					Object value=property.getValue(obj);
					dMap.put(property.getFieldName(), String.valueOf(value));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dMap;
	}
}
