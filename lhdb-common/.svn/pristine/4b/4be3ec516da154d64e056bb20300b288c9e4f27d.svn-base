package com.lhdb.game.util;

import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
import org.codehaus.jackson.JsonGenerator;  
import org.codehaus.jackson.JsonProcessingException;  
import org.codehaus.jackson.map.JsonSerializer;  
import org.codehaus.jackson.map.ObjectMapper;  
import org.codehaus.jackson.map.SerializerProvider;  
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

/**
 * title: 解决Date类型返回json格式为自定义格式
 * des: 
 * @author Horst
 * @date 2016年12月5日 下午6:29:08
 * @version V1.0
 */
public class CustomObjectMapper extends ObjectMapper{
	
	public CustomObjectMapper(){  
        CustomSerializerFactory factory = new CustomSerializerFactory();  
        factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){  
            @Override  
            public void serialize(Date value,   
                    JsonGenerator jsonGenerator,   
                    SerializerProvider provider)  
                    throws IOException, JsonProcessingException {  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                jsonGenerator.writeString(sdf.format(value));  
            }  
        });  
        this.setSerializerFactory(factory);  
    }  
}
