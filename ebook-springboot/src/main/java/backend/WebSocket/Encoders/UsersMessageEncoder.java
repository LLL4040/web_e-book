package backend.WebSocket.Encoders;

import backend.WebSocket.Messages.UsersMessage;
import org.apache.catalina.mbeans.UserMBean;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

/** Encode a UsersMessage as JSON.
 * For example,
 *   List<String> list = new ArrayList<>();
 *   list.add("Peter");
 *   list.add("Duke");
 *   new UsersMessage(list)
 * is encoded as follows:
 * {
 *   "type": "users",
 *   "userlist": [ "Peter", "Duke" ]
 * }
 */
public class UsersMessageEncoder implements Encoder.Text<UsersMessage> {
    @Override
    public void init(EndpointConfig ec) {}

    @Override
    public void destroy() {}

    @Override
    public String encode(UsersMessage usersMessage) throws EncodeException {
        StringWriter swriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
            jsonGen.writeStartObject()
                    .write("type", "users")
                    .writeStartArray("userList");
            for (String user : usersMessage.getUserList()) {
                jsonGen.write(user);
            }
            jsonGen.writeEnd().writeEnd();
        }
        return swriter.toString();
    }
}
