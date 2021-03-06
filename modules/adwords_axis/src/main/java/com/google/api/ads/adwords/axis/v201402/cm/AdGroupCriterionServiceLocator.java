/**
 * AdGroupCriterionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Mar 02, 2009 (07:08:06 PST) WSDL2Java emitter.
 */

package com.google.api.ads.adwords.axis.v201402.cm;

public class AdGroupCriterionServiceLocator extends org.apache.axis.client.Service implements com.google.api.ads.adwords.axis.v201402.cm.AdGroupCriterionService {

    public AdGroupCriterionServiceLocator() {
    }


    public AdGroupCriterionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AdGroupCriterionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AdGroupCriterionServiceInterfacePort
    private java.lang.String AdGroupCriterionServiceInterfacePort_address = "https://adwords.google.com/api/adwords/cm/v201402/AdGroupCriterionService";

    public java.lang.String getAdGroupCriterionServiceInterfacePortAddress() {
        return AdGroupCriterionServiceInterfacePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AdGroupCriterionServiceInterfacePortWSDDServiceName = "AdGroupCriterionServiceInterfacePort";

    public java.lang.String getAdGroupCriterionServiceInterfacePortWSDDServiceName() {
        return AdGroupCriterionServiceInterfacePortWSDDServiceName;
    }

    public void setAdGroupCriterionServiceInterfacePortWSDDServiceName(java.lang.String name) {
        AdGroupCriterionServiceInterfacePortWSDDServiceName = name;
    }

    public com.google.api.ads.adwords.axis.v201402.cm.AdGroupCriterionServiceInterface getAdGroupCriterionServiceInterfacePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AdGroupCriterionServiceInterfacePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAdGroupCriterionServiceInterfacePort(endpoint);
    }

    public com.google.api.ads.adwords.axis.v201402.cm.AdGroupCriterionServiceInterface getAdGroupCriterionServiceInterfacePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.google.api.ads.adwords.axis.v201402.cm.AdGroupCriterionServiceSoapBindingStub _stub = new com.google.api.ads.adwords.axis.v201402.cm.AdGroupCriterionServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getAdGroupCriterionServiceInterfacePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAdGroupCriterionServiceInterfacePortEndpointAddress(java.lang.String address) {
        AdGroupCriterionServiceInterfacePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.google.api.ads.adwords.axis.v201402.cm.AdGroupCriterionServiceInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                com.google.api.ads.adwords.axis.v201402.cm.AdGroupCriterionServiceSoapBindingStub _stub = new com.google.api.ads.adwords.axis.v201402.cm.AdGroupCriterionServiceSoapBindingStub(new java.net.URL(AdGroupCriterionServiceInterfacePort_address), this);
                _stub.setPortName(getAdGroupCriterionServiceInterfacePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AdGroupCriterionServiceInterfacePort".equals(inputPortName)) {
            return getAdGroupCriterionServiceInterfacePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://adwords.google.com/api/adwords/cm/v201402", "AdGroupCriterionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://adwords.google.com/api/adwords/cm/v201402", "AdGroupCriterionServiceInterfacePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AdGroupCriterionServiceInterfacePort".equals(portName)) {
            setAdGroupCriterionServiceInterfacePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
