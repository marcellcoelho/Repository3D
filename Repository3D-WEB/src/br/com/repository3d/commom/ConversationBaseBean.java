package br.com.repository3d.commom;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 * Classe base para beans com escopo de conversação.
 */
public class ConversationBaseBean implements Serializable {

    private static final long serialVersionUID = -851731542105639592L;


    @Inject
    private Conversation conversation;

    @PostConstruct
    public void startConversation() {
        if (this.conversation.isTransient()) {
            this.conversation.begin();

        }
    }

    public void endConversation() {
        if (!this.conversation.isTransient()) {
            this.conversation.end();
        }
    }


    public Conversation getConversation() {
        return this.conversation;
    }
}
