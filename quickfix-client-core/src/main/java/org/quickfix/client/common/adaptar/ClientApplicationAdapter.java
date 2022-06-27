package org.quickfix.client.common.adaptar;

import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.MsgType;
import quickfix.fix44.MessageCracker;

//https://github.com/gevoulga/spring-boot-quickfixj
//https://github.com/esanchezros/quickfixj-spring-boot-starter
@Slf4j
public class ClientApplicationAdapter implements Application {

	private final MessageCracker messageCracker;
	private final QuickfixConfig quickfixConfig;

	public ClientApplicationAdapter(MessageCracker messageCracker, QuickfixConfig quickfixConfig) {
		this.messageCracker = messageCracker;
		this.quickfixConfig = quickfixConfig;
	}

	@Override
	public void fromAdmin(Message message, SessionID sessionId) {
		log.info("fromAdmin: Message={}, SessionId={}", message, sessionId);
		try {
			messageCracker.crack(message, sessionId);
		} catch (UnsupportedMessageType | FieldNotFound | IncorrectTagValue e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void fromApp(Message message, SessionID sessionId) {
		log.info("fromApp: Message={}, SessionId={}", message, sessionId);

		try {
			final String msgType = message.getHeader().getString(MsgType.FIELD);
			log.info("fromApp: msgType {}", msgType);
			messageCracker.crack(message, sessionId);
		} catch (UnsupportedMessageType | FieldNotFound | IncorrectTagValue e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void onCreate(SessionID sessionId) {
		log.info("onCreate: SessionId={}", sessionId);
	}

	@Override
	public void onLogon(SessionID sessionId) {
		log.info("onLogon: SessionId={}", sessionId);
	}

	@Override
	public void onLogout(SessionID sessionId) {
		log.info("onLogout: SessionId={}", sessionId);
	}

	@Override
	public void toAdmin(Message message, SessionID sessionId) {
		log.info("toAdmin: Message={}, SessionId={}", message, sessionId);

		//https://stackoverflow.com/questions/55145892/quickfix-j-initiator-setup-username-and-password-at-logon
		try {
			final String msgType = message.getHeader().getString(MsgType.FIELD);
			log.info("toAdmin: msgType {}", msgType);
			if(MsgType.LOGON.compareTo(msgType) == 0) {
				log.info("Adding credentials...");
				message.setString(quickfix.field.Username.FIELD, quickfixConfig.getUserName());
				message.setString(quickfix.field.Password.FIELD, quickfixConfig.getPassword());
				log.info("toAdmin: {}", message);
			}
		} catch (FieldNotFound e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	@Override
	public void toApp(Message message, SessionID sessionId) {
		log.info("toApp: Message={}, SessionId={}", message, sessionId);
	}
}
