package javafx_predmetcrud;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.END_OBJECT;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import static javax.json.stream.JsonParser.Event.START_OBJECT;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider

@Produces(MediaType.APPLICATION_JSON)
@Consumes({"application/json"})
public class PredmetMessageBodyReader implements MessageBodyReader<List<Predmet>> {

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns,
            MediaType mt) {
        return true;
    }

    @Override
    public List<Predmet> readFrom(Class<List<Predmet>> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        if (mt.getType().equals("application") && mt.getSubtype().equals("json")) {
            Predmet predmet = new Predmet();
            List<Predmet> predmeti = new ArrayList();
            JsonParser parser = Json.createParser(in);
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        predmet = new Predmet();
                        break;
                    case END_OBJECT:
                        predmeti.add(predmet);
                        break;
                    case KEY_NAME:
                        String key = parser.getString();
                        parser.next();
                        switch (key) {

                            case "predmet_id":
                                predmet.setPredmet_id(parser.getInt());
                                break;
                            case "predmet_sifra":
                                predmet.setPredmet_sifra(parser.getString());
                                break;
                            case "predmet_naziv":
                                predmet.setPredmet_naziv(parser.getString());
                                break;

                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
            return predmeti;
        }
        throw new UnsupportedOperationException("Not supported MediaType: " + mt);
    }

}
