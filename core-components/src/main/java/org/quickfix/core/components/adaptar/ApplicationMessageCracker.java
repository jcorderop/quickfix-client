package org.quickfix.core.components.adaptar;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.quickfix.core.components.model.fix.QuickfixjMessageJsonTransformer;
import quickfix.*;
import quickfix.fix44.*;
import quickfix.fix44.Message;
import quickfix.fix44.MessageCracker;

@Slf4j
public class ApplicationMessageCracker extends MessageCracker {

	private final DataDictionary dataDictionary;

	public ApplicationMessageCracker(DataDictionary dataDictionary)  {
		this.dataDictionary = dataDictionary;
	}

	@Override
	public void onMessage(final MarketDataSnapshotFullRefresh message, final SessionID sessionID) {
		processMessage(message, sessionID);
	}

	@Override
	public void onMessage(MarketDataRequestReject message, SessionID sessionID) {
		processMessage(message, sessionID);
	}

	@Override
	public void onMessage(Reject message, SessionID sessionID) {
		processMessage(message, sessionID);
	}

	@Override
	public void onMessage(Logon message, SessionID sessionID) {
		processMessage(message, sessionID);
	}

	@Override
	public void onMessage(Logout message, SessionID sessionID) {
		processMessage(message, sessionID);
	}

	@Override
	public void onMessage(ExecutionReport message, SessionID sessionID) {
		processMessage(message, sessionID);
	}

	@Override
	public void onMessage(Heartbeat message, SessionID sessionID) {
		processMessage(message, sessionID);
	}

	private void processMessage(Message message, SessionID sessionID) {
		log.info("{} sessionID={}: {}", message.getClass().getSimpleName(), sessionID, message);
		final ObjectNode transform = new QuickfixjMessageJsonTransformer().transform(message, dataDictionary);
		System.out.println(transform.toPrettyString());
	}
}