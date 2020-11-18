/*
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.ditto.client.twin.internal;

public class UncompletedTwinConsumptionRequestException extends RuntimeException {

    private static final long serialVersionUID = -565137801315595348L;
    private static final String MESSAGE = "First consumption request on this channel must be completed first";

    /**
     * Constructs a new {@code UncompletedTwinConsumptionRequestException} object.
     */
    public UncompletedTwinConsumptionRequestException() {
        super(MESSAGE, null);
    }

}
