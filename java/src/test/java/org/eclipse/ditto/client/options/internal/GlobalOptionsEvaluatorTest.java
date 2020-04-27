/*
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
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
package org.eclipse.ditto.client.options.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mutabilitydetector.unittesting.AllowedReason.provided;
import static org.mutabilitydetector.unittesting.MutabilityAssert.assertInstancesOf;
import static org.mutabilitydetector.unittesting.MutabilityMatchers.areImmutable;

import java.util.UUID;

import org.eclipse.ditto.client.options.Option;
import org.eclipse.ditto.client.options.Options;
import org.eclipse.ditto.model.base.headers.DittoHeaders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit test for {@link OptionsEvaluator.Global}.
 */
@RunWith(MockitoJUnitRunner.class)
public final class GlobalOptionsEvaluatorTest {

    private static final DittoHeaders KNOWN_DITTO_HEADERS = DittoHeaders.newBuilder()
            .correlationId(UUID.randomUUID().toString())
            .build();
    private static final Option<DittoHeaders> DITTO_HEADERS_OPTION = Options.headers(KNOWN_DITTO_HEADERS);

    private OptionsEvaluator.Global underTest = null;


    @Before
    public void setUp() {
        final Option<?>[] options = new Option<?>[]{DITTO_HEADERS_OPTION};
        underTest = OptionsEvaluator.forGlobalOptions(options);
    }

    @Test
    public void assertImmutability() {
        assertInstancesOf(OptionsEvaluator.Global.class, areImmutable(),
                provided(OptionsEvaluator.class).isAlsoImmutable());
    }

    @Test
    public void tryToCreateInstanceWithNullOptions() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> OptionsEvaluator.forGlobalOptions(null))
                .withMessage("The options must not be null!");
    }

    @Test
    public void createInstanceWithEmptyOptions() {
        final OptionsEvaluator.Global underTest = OptionsEvaluator.forGlobalOptions(new Option<?>[0]);

        assertThat(underTest).isNotNull();
    }

    @Test
    public void getResponseTimeoutReturnsExpectedIfProvided() {
        assertThat(underTest.getDittoHeaders()).contains(DITTO_HEADERS_OPTION.getValue());
    }

    @Test
    public void getResponseTimeoutReturnsEmptyOptionalIfNotProvided() {
        final Option<?>[] options = new Option<?>[]{};
        underTest = OptionsEvaluator.forGlobalOptions(options);

        assertThat(underTest.getDittoHeaders()).isEmpty();
    }

}
