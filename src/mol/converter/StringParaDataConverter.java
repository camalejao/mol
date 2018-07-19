package mol.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringParaDataConverter implements Converter<LocalDateTime, String> {

	private final DateTimeFormatter formatter;
	
	public StringParaDataConverter(String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }
	
	@Override
	public String convert(LocalDateTime data) {
		if (data == null) {
            return null;
        }
		
		return data.format(formatter);
	}

}
