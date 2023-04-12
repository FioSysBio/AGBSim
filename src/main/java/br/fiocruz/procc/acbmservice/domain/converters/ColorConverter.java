package br.fiocruz.procc.acbmservice.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;

@Converter//(autoApply = true)
public class ColorConverter implements AttributeConverter<Color, String> {

    @Override
    public String convertToDatabaseColumn(Color attribute) {
//        String hex = "#"+Integer.toHexString(attribute.getRGB()).substring(0,6);
        String rgb = attribute.toString();
//        log.info("Convert "+attribute+" to "+hex);
        return rgb;
    }

    @Override
    public Color convertToEntityAttribute(String dbData) {
        String[] v;
        v = dbData.split(",");
        Color color = new Color(
                Integer.valueOf(v[0]), //R
                Integer.valueOf(v[1]), //G
                Integer.valueOf(v[2])  //B
        );

        return color;
    }
}
