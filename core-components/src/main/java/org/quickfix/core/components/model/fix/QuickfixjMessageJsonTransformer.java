package org.quickfix.core.components.model.fix;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import quickfix.*;

import java.util.Iterator;

//https://github.com/apache/camel/tree/main/components/camel-quickfix/src/test/java/org/apache/camel/component/quickfixj/examples/transform
public class QuickfixjMessageJsonTransformer {

    public ObjectNode transform(final Message message) {
        final SessionID sessionID = MessageUtils.getSessionID(message);
        final Session session = Session.lookupSession(sessionID);
        final DataDictionary dataDictionary = session.getDataDictionary();

        if (dataDictionary == null) {
            throw new IllegalStateException("No Data Dictionary. Exchange must reference an existing session");
        }

        return transform(message, dataDictionary);
    }

    public ObjectNode transform(final Message message, final DataDictionary dd) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectNode node = objectMapper.createObjectNode();
        if (message == null) {
            return node;
        } else {
            node.set("header", transform(message.getHeader(), objectMapper, dd));
            node.set("body", transform(message, objectMapper, dd));
            node.set("trailer", transform(message.getTrailer(), objectMapper, dd));
        }
        return node;
    }

    private ObjectNode transform(final FieldMap fieldMap,
                                 final ObjectMapper objectMapper,
                                 final DataDictionary dd) {
        //https://github.com/java-code-examples/jackson-fasterxml-examples/blob/master/pom.xml
        final ObjectNode node = objectMapper.createObjectNode();
        mapFields(fieldMap, dd, node);
        mapGroups(fieldMap, objectMapper, dd, node);
        return node;
    }

    private void mapGroups(final FieldMap fieldMap,
                           final ObjectMapper objectMapper,
                           final DataDictionary dd,
                           final ObjectNode node) {
        final Iterator<Integer> groupKeys = fieldMap.groupKeyIterator();
        while (groupKeys.hasNext()) {
            final ArrayNode arrayNode = objectMapper.createArrayNode();
            final int groupTag = groupKeys.next();
            final String groupName = dd.getFieldName(groupTag);
            for (Group group : fieldMap.getGroups(groupTag)) {
                arrayNode.add(transform(group, objectMapper, dd));
            }
            node.set(groupName, arrayNode);
        }
    }

    private void mapFields(final FieldMap fieldMap,
                           final DataDictionary dd,
                           final ObjectNode node) {
        final Iterator<Field<?>> fieldIterator = fieldMap.iterator();
        while (fieldIterator.hasNext()) {
            final Field<?> field = fieldIterator.next();
            final String fieldName = dd.getFieldName(field.getField());
            if (dd.hasFieldValue(field.getField())) { //Enum
                node.put(fieldName, dd.getValueName(field.getField(), field.getObject().toString()));
            } else {
                final FieldType fieldType = dd.getFieldType(field.getField());
                if (Double.class.isAssignableFrom(fieldType.getJavaType())) {
                    node.put(fieldName, Double.valueOf(field.getObject().toString()));
                } else if (Integer.class.isAssignableFrom(fieldType.getJavaType())) {
                    node.put(fieldName, Integer.valueOf(field.getObject().toString()));
                } else {
                    node.put(fieldName, field.getObject().toString());
                }
            }
        }
    }
}