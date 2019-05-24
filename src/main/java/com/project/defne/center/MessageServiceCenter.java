package com.project.defne.center;

import org.springframework.stereotype.Component;

/**
 * Created by Ceren A. @ 5/18/2019
 * While listening ${SPOT}
 */
@Component
public class MessageServiceCenter {
    /**
     * Sends a message submission request to the GSM operator's network. Later GSM operator
     * will forward the request to the recipient and returns the result code of the operation.
     *
     * Available result codes:
     * 0: Success
     * 1: Number unreachable
     * 2: Network error
     * 3: Unknown error
     *
     * @param senderAddress         The number which will be shown on recipient's cell phone.
     * @param destinationNumber     Recipient's cell phone number.
     * @param messageBody           Message text to be sent to the recipient.
     * @return
     */
    public int submitMessage(String senderAddress, String destinationNumber, String messageBody) {
        // For simplicity return a random number between 0 and 3 as a result code
        return (int) (Math.random() * 4);
    }
}

