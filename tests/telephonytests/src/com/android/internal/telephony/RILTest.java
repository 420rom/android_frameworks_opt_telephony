/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.telephony;

import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ALLOW_DATA;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CHANGE_SIM_PIN;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CHANGE_SIM_PIN2;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CONFERENCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DATA_REGISTRATION_STATE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DELETE_SMS_ON_SIM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DEVICE_IDENTITY;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DTMF;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_NETWORK_DEPERSONALIZATION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_SIM_PIN;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_SIM_PIN2;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_SIM_PUK;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_SIM_PUK2;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_ACTIVITY_INFO;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_CELL_INFO_LIST;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_CURRENT_CALLS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_HARDWARE_CONFIG;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_IMSI;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_RADIO_CAPABILITY;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_SIM_STATUS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_SMSC_ADDRESS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_HANGUP;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_IMS_REGISTRATION_STATE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ISIM_AUTHENTICATION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_LAST_CALL_FAIL_CAUSE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_NV_READ_ITEM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_NV_RESET_CONFIG;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_NV_WRITE_ITEM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_OPERATOR;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_PULL_LCEDATA;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_RADIO_POWER;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_REPORT_SMS_MEMORY_STATUS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SEND_DEVICE_STATE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SEND_SMS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SEND_SMS_EXPECT_MORE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_INITIAL_ATTACH_APN;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_SIM_CARD_POWER;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_SMSC_ADDRESS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_UNSOLICITED_RESPONSE_FILTER;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_UNSOL_CELL_INFO_LIST_RATE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SHUTDOWN;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SIGNAL_STRENGTH;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SIM_AUTHENTICATION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SIM_CLOSE_CHANNEL;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SIM_OPEN_CHANNEL;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_START_LCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_STOP_LCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_UDUB;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_VOICE_RADIO_TECH;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_VOICE_REGISTRATION_STATE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_WRITE_SMS_TO_SIM;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.hardware.radio.V1_0.DataProfileInfo;
import android.hardware.radio.V1_0.GsmSmsMessage;
import android.hardware.radio.V1_0.IRadio;
import android.hardware.radio.V1_0.NvWriteItem;
import android.hardware.radio.V1_0.RadioError;
import android.hardware.radio.V1_0.RadioResponseInfo;
import android.hardware.radio.V1_0.RadioResponseType;
import android.hardware.radio.V1_0.SmsWriteArgs;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IPowerManager;
import android.os.Message;
import android.os.PowerManager;
import android.os.WorkSource;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.RIL.RilHandler;
import com.android.internal.telephony.dataconnection.ApnSetting;
import com.android.internal.telephony.dataconnection.DataProfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class RILTest extends TelephonyTest {

    // refer to RIL#DEFAULT_BLOCKING_MESSAGE_RESPONSE_TIMEOUT_MS
    private static final int DEFAULT_BLOCKING_MESSAGE_RESPONSE_TIMEOUT_MS = 2000;

    // refer to RIL#DEFAULT_WAKE_LOCK_TIMEOUT_MS
    private static final int DEFAULT_WAKE_LOCK_TIMEOUT_MS = 60000;

    @Mock
    private ConnectivityManager mConnectionManager;
    @Mock
    private IRadio mRadioProxy;

    private RilHandler mRilHandler;
    private RIL mRILInstance;
    private RIL mRILUnderTest;
    private RILTestHandler mTestHandler;
    ArgumentCaptor<Integer> mSerialNumberCaptor = ArgumentCaptor.forClass(Integer.class);

    private class RILTestHandler extends HandlerThread {

        RILTestHandler(String name) {
            super(name);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            createTelephonyDevController();
            Context context = new ContextFixture().getTestDouble();
            doReturn(true).when(mConnectionManager)
                    .isNetworkSupported(ConnectivityManager.TYPE_MOBILE);
            doReturn(mConnectionManager).when(context)
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            PowerManager powerManager = createPowerManager(context);
            doReturn(powerManager).when(context).getSystemService(Context.POWER_SERVICE);
            doReturn(new ApplicationInfo()).when(context).getApplicationInfo();

            mRILInstance = new RIL(context, RILConstants.PREFERRED_NETWORK_MODE,
                    Phone.PREFERRED_CDMA_SUBSCRIPTION, 0);
            mRILUnderTest = spy(mRILInstance);
            doReturn(mRadioProxy).when(mRILUnderTest).getRadioProxy(any());

            mRilHandler = mRILUnderTest.getRilHandler();

            setReady(true);
        }

        private PowerManager createPowerManager(Context context) {
            return new PowerManager(context, mock(IPowerManager.class), new Handler(getLooper()));
        }

        private void createTelephonyDevController() {
            try {
                TelephonyDevController.create();
            } catch (RuntimeException e) {
            }
        }
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mTestHandler = new RILTestHandler(getClass().getSimpleName());
        mTestHandler.start();
        waitUntilReady();
    }

    @After
    public void tearDown() throws Exception {
        mTestHandler.quit();
    }

    @Test
    public void testGetIccCardStatus() throws Exception {
        mRILUnderTest.getIccCardStatus(obtainMessage());
        verify(mRadioProxy).getIccCardStatus(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_GET_SIM_STATUS);
    }

    @Test
    public void testSupplyIccPinForApp() throws Exception {
        String pin = "1234";
        String aid = "2345";
        mRILUnderTest.supplyIccPinForApp(pin, aid, obtainMessage());
        verify(mRadioProxy).supplyIccPinForApp(mSerialNumberCaptor.capture(), eq(pin), eq(aid));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_ENTER_SIM_PIN);
    }

    @Test
    public void testSupplyIccPukForApp() throws Exception {
        String puk = "pukcode";
        String newPin = "1314";
        String aid = "2345";
        mRILUnderTest.supplyIccPukForApp(puk, newPin, aid, obtainMessage());
        verify(mRadioProxy)
                .supplyIccPukForApp(mSerialNumberCaptor.capture(), eq(puk), eq(newPin), eq(aid));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_ENTER_SIM_PUK);
    }

    @Test
    public void testSupplyIccPin2ForApp() throws Exception {
        String pin = "1234";
        String aid = "2345";
        mRILUnderTest.supplyIccPin2ForApp(pin, aid, obtainMessage());
        verify(mRadioProxy).supplyIccPin2ForApp(
                mSerialNumberCaptor.capture(), eq(pin), eq(aid));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_ENTER_SIM_PIN2);
    }

    @Test
    public void testSupplyIccPuk2ForApp() throws Exception {
        String puk = "pukcode";
        String newPin = "1314";
        String aid = "2345";
        mRILUnderTest.supplyIccPuk2ForApp(puk, newPin, aid, obtainMessage());
        verify(mRadioProxy)
                .supplyIccPuk2ForApp(mSerialNumberCaptor.capture(), eq(puk), eq(newPin), eq(aid));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_ENTER_SIM_PUK2);
    }

    @Test
    public void testChangeIccPinForApp() throws Exception {
        String oldPin = "1234";
        String newPin = "1314";
        String aid = "2345";
        mRILUnderTest.changeIccPinForApp(oldPin, newPin, aid, obtainMessage());
        verify(mRadioProxy).changeIccPinForApp(
                mSerialNumberCaptor.capture(), eq(oldPin), eq(newPin), eq(aid));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_CHANGE_SIM_PIN);
    }

    @Test
    public void testChangeIccPin2ForApp() throws Exception {
        String oldPin2 = "1234";
        String newPin2 = "1314";
        String aid = "2345";
        mRILUnderTest.changeIccPin2ForApp(oldPin2, newPin2, aid, obtainMessage());
        verify(mRadioProxy).changeIccPin2ForApp(
                mSerialNumberCaptor.capture(), eq(oldPin2), eq(newPin2), eq(aid));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_CHANGE_SIM_PIN2);
    }

    @Test
    public void testSupplyNetworkDepersonalization() throws Exception {
        String netpin = "1234";
        mRILUnderTest.supplyNetworkDepersonalization(netpin, obtainMessage());
        verify(mRadioProxy).supplyNetworkDepersonalization(
                mSerialNumberCaptor.capture(), eq(netpin));
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_ENTER_NETWORK_DEPERSONALIZATION);
    }

    @Test
    public void testGetCurrentCalls() throws Exception {
        mRILUnderTest.getCurrentCalls(obtainMessage());
        verify(mRadioProxy).getCurrentCalls(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_GET_CURRENT_CALLS);
    }

    @Test
    public void testGetIMSIForApp() throws Exception {
        String aid = "1234";
        mRILUnderTest.getIMSIForApp(aid, obtainMessage());
        verify(mRadioProxy).getImsiForApp(mSerialNumberCaptor.capture(), eq(aid));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_GET_IMSI);
    }

    @Test
    public void testHangupWaitingOrBackground() throws Exception {
        mRILUnderTest.hangupWaitingOrBackground(obtainMessage());
        verify(mRadioProxy).hangupWaitingOrBackground(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND);
    }

    @Test
    public void testHangupForegroundResumeBackground() throws Exception {
        mRILUnderTest.hangupForegroundResumeBackground(obtainMessage());
        verify(mRadioProxy).hangupForegroundResumeBackground(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND);
    }

    @Test
    public void testHangupConnection() throws Exception {
        int gsmIndex = 0;
        mRILUnderTest.hangupConnection(gsmIndex, obtainMessage());
        verify(mRadioProxy).hangup(mSerialNumberCaptor.capture(), eq(gsmIndex));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_HANGUP);
    }

    @Test
    public void testSwitchWaitingOrHoldingAndActive() throws Exception {
        mRILUnderTest.switchWaitingOrHoldingAndActive(obtainMessage());
        verify(mRadioProxy).switchWaitingOrHoldingAndActive(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE);
    }

    @Test
    public void testConference() throws Exception {
        mRILUnderTest.conference(obtainMessage());
        verify(mRadioProxy).conference(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_CONFERENCE);
    }

    @Test
    public void testRejectCall() throws Exception {
        mRILUnderTest.rejectCall(obtainMessage());
        verify(mRadioProxy).rejectCall(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_UDUB);
    }

    @Test
    public void testGetLastCallFailCause() throws Exception {
        mRILUnderTest.getLastCallFailCause(obtainMessage());
        verify(mRadioProxy).getLastCallFailCause(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_LAST_CALL_FAIL_CAUSE);
    }

    @Test
    public void testGetSignalStrength() throws Exception {
        mRILUnderTest.getSignalStrength(obtainMessage());
        verify(mRadioProxy).getSignalStrength(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SIGNAL_STRENGTH);
    }

    @Test
    public void testGetVoiceRegistrationState() throws Exception {
        mRILUnderTest.getVoiceRegistrationState(obtainMessage());
        verify(mRadioProxy).getVoiceRegistrationState(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_VOICE_REGISTRATION_STATE);
    }

    @Test
    public void testGetDataRegistrationState() throws Exception {
        mRILUnderTest.getDataRegistrationState(obtainMessage());
        verify(mRadioProxy).getDataRegistrationState(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_DATA_REGISTRATION_STATE);
    }

    @Test
    public void testGetOperator() throws Exception {
        mRILUnderTest.getOperator(obtainMessage());
        verify(mRadioProxy).getOperator(mSerialNumberCaptor.capture());
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_OPERATOR);
    }

    @Test
    public void testSetRadioPower() throws Exception {
        boolean on = true;
        mRILUnderTest.setRadioPower(on, obtainMessage());
        verify(mRadioProxy).setRadioPower(mSerialNumberCaptor.capture(), eq(on));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_RADIO_POWER);
    }

    @Test
    public void testSendDtmf() throws Exception {
        char c = 'c';
        mRILUnderTest.sendDtmf(c, obtainMessage());
        verify(mRadioProxy).sendDtmf(mSerialNumberCaptor.capture(), eq(c + ""));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_DTMF);
    }

    @Test
    public void testSendSMS() throws Exception {
        String smscPdu = "smscPdu";
        String pdu = "pdu";
        GsmSmsMessage msg = new GsmSmsMessage();
        msg.smscPdu = smscPdu;
        msg.pdu = pdu;
        mRILUnderTest.sendSMS(smscPdu, pdu, obtainMessage());
        verify(mRadioProxy).sendSms(mSerialNumberCaptor.capture(), eq(msg));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SEND_SMS);
    }

    @Test
    public void testSendSMSExpectMore() throws Exception {
        String smscPdu = "smscPdu";
        String pdu = "pdu";
        GsmSmsMessage msg = new GsmSmsMessage();
        msg.smscPdu = smscPdu;
        msg.pdu = pdu;
        mRILUnderTest.sendSMSExpectMore(smscPdu, pdu, obtainMessage());
        verify(mRadioProxy).sendSMSExpectMore(mSerialNumberCaptor.capture(), eq(msg));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SEND_SMS_EXPECT_MORE);
    }

    @Test
    public void testWriteSmsToSim() throws Exception {
        String smscPdu = "smscPdu";
        String pdu = "pdu";
        int status = SmsManager.STATUS_ON_ICC_READ;
        SmsWriteArgs args = new SmsWriteArgs();
        args.status = 1;
        args.smsc = smscPdu;
        args.pdu = pdu;
        mRILUnderTest.writeSmsToSim(status, smscPdu, pdu, obtainMessage());
        verify(mRadioProxy).writeSmsToSim(mSerialNumberCaptor.capture(), eq(args));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_WRITE_SMS_TO_SIM);
    }

    @Test
    public void testDeleteSmsOnSim() throws Exception {
        int index = 0;
        mRILUnderTest.deleteSmsOnSim(index, obtainMessage());
        verify(mRadioProxy).deleteSmsOnSim(mSerialNumberCaptor.capture(), eq(index));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_DELETE_SMS_ON_SIM);
    }

    @Test
    public void testGetDeviceIdentity() throws Exception {
        mRILUnderTest.getDeviceIdentity(obtainMessage());
        verify(mRadioProxy).getDeviceIdentity(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_DEVICE_IDENTITY);
    }

    @Test
    public void testExitEmergencyCallbackMode() throws Exception {
        mRILUnderTest.exitEmergencyCallbackMode(obtainMessage());
        verify(mRadioProxy).exitEmergencyCallbackMode(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE);
    }

    @Test
    public void testGetSmscAddress() throws Exception {
        mRILUnderTest.getSmscAddress(obtainMessage());
        verify(mRadioProxy).getSmscAddress(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_GET_SMSC_ADDRESS);
    }

    @Test
    public void testSetSmscAddress() throws Exception {
        String address = "address";
        mRILUnderTest.setSmscAddress(address, obtainMessage());
        verify(mRadioProxy).setSmscAddress(mSerialNumberCaptor.capture(), eq(address));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SET_SMSC_ADDRESS);
    }

    @Test
    public void testReportSmsMemoryStatus() throws Exception {
        boolean available = true;
        mRILUnderTest.reportSmsMemoryStatus(available, obtainMessage());
        verify(mRadioProxy).reportSmsMemoryStatus(mSerialNumberCaptor.capture(), eq(available));
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_REPORT_SMS_MEMORY_STATUS);
    }

    @Test
    public void testReportStkServiceIsRunning() throws Exception {
        mRILUnderTest.reportStkServiceIsRunning(obtainMessage());
        verify(mRadioProxy).reportStkServiceIsRunning(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING);
    }

    @Test
    public void testGetCdmaSubscriptionSource() throws Exception {
        mRILUnderTest.getCdmaSubscriptionSource(obtainMessage());
        verify(mRadioProxy).getCdmaSubscriptionSource(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE);
    }

    @Test
    public void testRequestIsimAuthentication() throws Exception {
        String nonce = "nonce";
        mRILUnderTest.requestIsimAuthentication(nonce, obtainMessage());
        verify(mRadioProxy).requestIsimAuthentication(mSerialNumberCaptor.capture(), eq(nonce));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_ISIM_AUTHENTICATION);
    }

    @Test
    public void testAcknowledgeIncomingGsmSmsWithPdu() throws Exception {
        boolean success = true;
        String ackPdu = "ackPdu";
        mRILUnderTest.acknowledgeIncomingGsmSmsWithPdu(success, ackPdu, obtainMessage());
        verify(mRadioProxy).acknowledgeIncomingGsmSmsWithPdu(
                mSerialNumberCaptor.capture(), eq(success), eq(ackPdu));
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU);
    }

    @Test
    public void testGetVoiceRadioTechnology() throws Exception {
        mRILUnderTest.getVoiceRadioTechnology(obtainMessage());
        verify(mRadioProxy).getVoiceRadioTechnology(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_VOICE_RADIO_TECH);
    }

    @Test
    public void testGetCellInfoList() throws Exception {
        mRILUnderTest.getCellInfoList(obtainMessage(), null);
        verify(mRadioProxy).getCellInfoList(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_GET_CELL_INFO_LIST);
    }

    @Test
    public void testSetCellInfoListRate() throws Exception {
        int rateInMillis = 1000;
        mRILUnderTest.setCellInfoListRate(rateInMillis, obtainMessage(), null);
        verify(mRadioProxy).setCellInfoListRate(mSerialNumberCaptor.capture(), eq(rateInMillis));
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_SET_UNSOL_CELL_INFO_LIST_RATE);
    }

    @Test
    public void testSetInitialAttachApn() throws Exception {
        ApnSetting apnSetting = new ApnSetting(
                -1, "22210", "Vodafone IT", "web.omnitel.it", "", "",
                "", "", "", "", "", 0, new String[]{"DUN"}, "IP", "IP", true, 0, 0,
                0, false, 0, 0, 0, 0, "", "");
        DataProfile dataProfile = new DataProfile(apnSetting);
        boolean isRoaming = false;

        mRILUnderTest.setInitialAttachApn(dataProfile, isRoaming, obtainMessage());
        verify(mRadioProxy).setInitialAttachApn(
                mSerialNumberCaptor.capture(),
                eq((DataProfileInfo) invokeMethod(
                        mRILInstance,
                        "convertToHalDataProfile",
                        new Class<?>[] {DataProfile.class},
                        new Object[] {dataProfile})),
                eq(dataProfile.modemCognitive),
                eq(isRoaming));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SET_INITIAL_ATTACH_APN);
    }

    @Test
    public void testGetImsRegistrationState() throws Exception {
        mRILUnderTest.getImsRegistrationState(obtainMessage());
        verify(mRadioProxy).getImsRegistrationState(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_IMS_REGISTRATION_STATE);
    }

    @Test
    public void testIccOpenLogicalChannel() throws Exception {
        String aid = "aid";
        int p2 = 0;
        mRILUnderTest.iccOpenLogicalChannel(aid, p2, obtainMessage());
        verify(mRadioProxy).iccOpenLogicalChannel(mSerialNumberCaptor.capture(), eq(aid), eq(p2));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SIM_OPEN_CHANNEL);
    }

    @Test
    public void testIccCloseLogicalChannel() throws Exception {
        int channel = 1;
        mRILUnderTest.iccCloseLogicalChannel(channel, obtainMessage());
        verify(mRadioProxy).iccCloseLogicalChannel(mSerialNumberCaptor.capture(), eq(channel));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SIM_CLOSE_CHANNEL);
    }

    @Test
    public void testNvWriteItem() throws Exception {
        int itemId = 1;
        String itemValue = "value";
        mRILUnderTest.nvWriteItem(itemId, itemValue, obtainMessage());
        NvWriteItem item = new NvWriteItem();
        item.itemId = itemId;
        item.value = itemValue;
        verify(mRadioProxy).nvWriteItem(mSerialNumberCaptor.capture(), eq(item));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_NV_WRITE_ITEM);
    }

    @Test
    public void testNvReadItem() throws Exception {
        int itemId = 1;
        mRILUnderTest.nvReadItem(itemId, obtainMessage());
        verify(mRadioProxy).nvReadItem(mSerialNumberCaptor.capture(), eq(itemId));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_NV_READ_ITEM);
    }

    @Test
    public void testNvResetConfig() throws Exception {
        int resetType = 1;
        mRILUnderTest.nvResetConfig(resetType, obtainMessage());
        verify(mRadioProxy).nvResetConfig(
                mSerialNumberCaptor.capture(),
                eq((Integer) invokeMethod(
                        mRILInstance,
                        "convertToHalResetNvType",
                        new Class<?>[] {Integer.TYPE},
                        new Object[] {resetType})));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_NV_RESET_CONFIG);
    }

    @Test
    public void testSetDataAllowed() throws Exception {
        boolean allowed = true;
        mRILUnderTest.setDataAllowed(allowed, obtainMessage());
        verify(mRadioProxy).setDataAllowed(mSerialNumberCaptor.capture(), eq(allowed));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_ALLOW_DATA);
    }

    @Test
    public void testGetHardwareConfig() throws Exception {
        mRILUnderTest.getHardwareConfig(obtainMessage());
        verify(mRadioProxy).getHardwareConfig(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_GET_HARDWARE_CONFIG);
    }

    @Test
    public void testRequestIccSimAuthentication() throws Exception {
        int authContext = 1;
        String data = "data";
        String aid = "aid";
        mRILUnderTest.requestIccSimAuthentication(authContext, data, aid, obtainMessage());
        verify(mRadioProxy).requestIccSimAuthentication(
                mSerialNumberCaptor.capture(), eq(authContext), eq(data), eq(aid));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SIM_AUTHENTICATION);
    }

    @Test
    public void testRequestShutdown() throws Exception {
        mRILUnderTest.requestShutdown(obtainMessage());
        verify(mRadioProxy).requestShutdown(mSerialNumberCaptor.capture());
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SHUTDOWN);
    }

    @Test
    public void testGetRadioCapability() throws Exception {
        mRILUnderTest.getRadioCapability(obtainMessage());
        verify(mRadioProxy).getRadioCapability(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_GET_RADIO_CAPABILITY);
    }

    @Test
    public void testStartLceService() throws Exception {
        int reportIntervalMs = 1000;
        boolean pullMode = false;
        mRILUnderTest.startLceService(reportIntervalMs, pullMode, obtainMessage());
        verify(mRadioProxy).startLceService(
                mSerialNumberCaptor.capture(), eq(reportIntervalMs), eq(pullMode));
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_START_LCE);
    }

    @Test
    public void testStopLceService() throws Exception {
        mRILUnderTest.stopLceService(obtainMessage());
        verify(mRadioProxy).stopLceService(mSerialNumberCaptor.capture());
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_STOP_LCE);
    }

    @Test
    public void testPullLceData() throws Exception {
        mRILUnderTest.pullLceData(obtainMessage());
        verify(mRadioProxy).pullLceData(mSerialNumberCaptor.capture());
        verifyRILResponse(mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_PULL_LCEDATA);
    }

    @Test
    public void testGetModemActivityInfo() throws Exception {
        mRILUnderTest.getModemActivityInfo(obtainMessage());
        verify(mRadioProxy).getModemActivityInfo(mSerialNumberCaptor.capture());
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_GET_ACTIVITY_INFO);
    }

    @Test
    public void testGetModemActivityInfoTimeout() {
        mRILUnderTest.getModemActivityInfo(obtainMessage());
        assertEquals(1, mRILUnderTest.getRilRequestList().size());
        waitForHandlerActionDelayed(mRilHandler, 10, DEFAULT_BLOCKING_MESSAGE_RESPONSE_TIMEOUT_MS);
        assertEquals(0, mRILUnderTest.getRilRequestList().size());
    }

    @Test
    public void testSendDeviceState() throws Exception {
        int stateType = 1;
        boolean state = false;
        mRILUnderTest.sendDeviceState(stateType, state, obtainMessage());
        verify(mRadioProxy).sendDeviceState(
                mSerialNumberCaptor.capture(), eq(stateType), eq(state));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SEND_DEVICE_STATE);
    }

    @Test
    public void testSetUnsolResponseFilter() throws Exception {
        int filter = 1;
        mRILUnderTest.setUnsolResponseFilter(filter, obtainMessage());
        verify(mRadioProxy).setIndicationFilter(mSerialNumberCaptor.capture(), eq(filter));
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_SET_UNSOLICITED_RESPONSE_FILTER);
    }

    @Test
    public void testSetSimCardPowerForPowerDownState() throws Exception {
        mRILUnderTest.setSimCardPower(TelephonyManager.CARD_POWER_DOWN, obtainMessage());
        verify(mRadioProxy).setSimCardPower(mSerialNumberCaptor.capture(), eq(false));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SET_SIM_CARD_POWER);
    }

    @Test
    public void testSetSimCardPowerForPowerUpState() throws Exception {
        mRILUnderTest.setSimCardPower(TelephonyManager.CARD_POWER_UP, obtainMessage());
        verify(mRadioProxy).setSimCardPower(mSerialNumberCaptor.capture(), eq(true));
        verifyRILResponse(
                mRILUnderTest, mSerialNumberCaptor.getValue(), RIL_REQUEST_SET_SIM_CARD_POWER);
    }

    @Test
    public void testHandleCallSetupRequestFromSim() throws Exception {
        boolean accept = true;
        mRILUnderTest.handleCallSetupRequestFromSim(accept, obtainMessage());
        verify(mRadioProxy).handleStkCallSetupRequestFromSim(
                mSerialNumberCaptor.capture(), eq(accept));
        verifyRILResponse(
                mRILUnderTest,
                mSerialNumberCaptor.getValue(),
                RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM);
    }

    @Test
    public void testWakeLockTimeout() throws Exception {
        invokeMethod(
                mRILInstance,
                "obtainRequest",
                new Class<?>[] {Integer.TYPE, Message.class, WorkSource.class},
                new Object[] {RIL_REQUEST_GET_SIM_STATUS, obtainMessage(), null});

        // The wake lock should be held when obtain a RIL request.
        assertTrue(mRILInstance.getWakeLock(RIL.FOR_WAKELOCK).isHeld());

        waitForHandlerActionDelayed(mRilHandler, 10, DEFAULT_WAKE_LOCK_TIMEOUT_MS);

        // The wake lock should be released after processed the time out event.
        assertFalse(mRILInstance.getWakeLock(RIL.FOR_WAKELOCK).isHeld());
    }

    private Message obtainMessage() {
        return mTestHandler.getThreadHandler().obtainMessage();
    }

    private static void verifyRILResponse(RIL ril, int serial, int requestType) {
        RadioResponseInfo responseInfo =
                createFakeRadioResponseInfo(serial, RadioError.NONE, RadioResponseType.SOLICITED);

        RILRequest rr = ril.processResponse(responseInfo);
        assertNotNull(rr);

        assertEquals(serial, rr.getSerial());
        assertEquals(requestType, rr.getRequest());
        assertTrue(ril.getWakeLock(RIL.FOR_WAKELOCK).isHeld());

        ril.processResponseDone(rr, responseInfo, null);
        assertEquals(0, ril.getRilRequestList().size());
        assertFalse(ril.getWakeLock(RIL.FOR_WAKELOCK).isHeld());
    }

    private static Object invokeMethod(
            Object instance, String methodName, Class<?>[] parameterClasses, Object[] parameters) {
        try {
            Method method = instance.getClass().getDeclaredMethod(methodName, parameterClasses);
            method.setAccessible(true);
            return method.invoke(instance, parameters);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            fail(instance.getClass() + " " + methodName + " " + e.getClass().getName());
        }
        return null;
    }

    private static RadioResponseInfo createFakeRadioResponseInfo(int serial, int error, int type) {
        RadioResponseInfo respInfo = new RadioResponseInfo();
        respInfo.serial = serial;
        respInfo.error = error;
        respInfo.type = type;
        return respInfo;
    }
}
