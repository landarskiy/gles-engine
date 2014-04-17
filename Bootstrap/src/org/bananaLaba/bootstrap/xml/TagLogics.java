package org.bananaLaba.bootstrap.xml;

import org.bananaLaba.bootstrap.common.AttributeMap;

public interface TagLogics {

    void handle(final AttributeMap attributes);

    void handleCharacterData(final String data);

}
