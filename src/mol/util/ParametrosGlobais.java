package mol.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParametrosGlobais {
	
	private static Properties getProp() throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        InputStream file = classLoader.getResourceAsStream("META-INF/dados.properties");
        props.load(file);
        return props;
    }
	
	
	private static String getProperty(String propriedade) {
		try {
			Properties prop = getProp();
			return prop.getProperty(propriedade);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final Integer PERCENTUAL_APRENDIZAGEM_MINIMO = 75;
	public static final String ENDERECO_EMAIL = getProperty("prop.email.endereco");
	public static final String SENHA_EMAIL = getProperty("prop.email.senha");
	
}
