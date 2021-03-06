package ru.kostikov.menu;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Алексей on 19.09.2016.
 */
public class SimpleGenerator {


    /**
     * It substitutes value in Patter to params in map
     * @param pattern
     * @param params
     * @return
     */
    String generate(Optional<String> pattern, Optional<Map<String, String>>  params) throws GeneratorExeption{
        Pattern regExp = Pattern.compile("\\$\\{(\\w+)\\}");
        StringBuffer sb = new StringBuffer();

        if (pattern.isPresent() && params.isPresent()){
            int counter = 0;
            Matcher matcher = regExp.matcher(pattern.get());

            while (matcher.find()){
                counter++;
                if (params.get().containsKey(matcher.group(1))){
                    matcher.appendReplacement(sb, params.get().get(matcher.group(1)));
                }else {
                    throw new GeneratorExeption("A required parameter was not found");
                }
            }
            if (counter != params.get().size()){
                throw new GeneratorExeption("Have extra params");
            }
            matcher.appendTail(sb);
        }else {
            throw new GeneratorExeption("Params or pattern was not found");
        }

        return sb.toString();
    }
}
