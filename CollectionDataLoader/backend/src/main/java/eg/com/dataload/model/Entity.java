//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.02.25 at 09:59:43 AM GMT+02:00 
//


package eg.com.dataload.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eg.com.dataload.util.CommonUtil;
import eg.com.dataload.util.JAXBCdataAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parameters">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Param" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="data-type" type="{}DataType"/>
 *                             &lt;element name="date-formate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="colomn" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="data-type" type="{}DataType"/>
 *                   &lt;element name="date-formate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="query" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="mapping" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parameters",
    "colomn"
})
@XmlRootElement(name = "entity")
public class Entity {

    @XmlElement(required = true)
    protected Entity.Parameters parameters;
    protected List<Entity.Colomn> colomn;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "target")
    protected String target;

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Entity.Parameters }
     *     
     */
    public Entity.Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entity.Parameters }
     *     
     */
    public void setParameters(Entity.Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the colomn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the colomn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColomn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Entity.Colomn }
     * 
     * 
     */
    public List<Entity.Colomn> getColomn() {
        if (colomn == null) {
            colomn = new ArrayList<Entity.Colomn>();
        }
        return this.colomn;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }
    
    public String getInsertStatement(){
    	
    	StringBuilder insertClause = new StringBuilder();
    	insertClause.append("Insert Into "+getName() +"(");
    	
    	StringBuilder valuesClause = new StringBuilder();
    	valuesClause.append(" Values" +"(");
    	
    	
    	
    	Collections.sort(getColomn());
    	int colomnsCount = getColomn().size();
    	for(Colomn c:getColomn()){
    		insertClause.append(c.getName());
    		valuesClause.append(":"+c.getName());
    		if(c.getOrder()<colomnsCount){
    			insertClause.append(",");
    			valuesClause.append(",");
    		}else{
    			insertClause.append(")");
    			valuesClause.append(")");
    		}
    	}
    	
    	insertClause.append(valuesClause);
    	
    	return insertClause.toString();
    	
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="data-type" type="{}DataType"/>
     *         &lt;element name="date-formate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="query" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="mapping" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "order",
        "value",
        "dataType",
        "dateFormate",
        "source",
        "query",
        "mapping"
    })
    public static class Colomn implements Comparable<Colomn>{

        @XmlElement(required = true)
        protected String name;
        protected int order;
        @XmlElement(required = true)
        protected String value;
        @XmlElement(name = "data-type", required = true)
        @XmlSchemaType(name = "string")
        protected DataType dataType;
        @XmlElement(name = "date-formate", required = true)
        protected String dateFormate;
        @XmlElement(required = true)
        protected String source;
        @XmlElement(required = true)
        @XmlJavaTypeAdapter(value=JAXBCdataAdapter.class)
        protected String query;
        protected List<Entity.Colomn.Mapping> mapping;
        @XmlAttribute(name = "key")
        protected Boolean key;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the order property.
         * 
         */
        public int getOrder() {
            return order;
        }

        /**
         * Sets the value of the order property.
         * 
         */
        public void setOrder(int value) {
            this.order = value;
        }

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the dataType property.
         * 
         * @return
         *     possible object is
         *     {@link DataType }
         *     
         */
        public DataType getDataType() {
            return dataType;
        }

        /**
         * Sets the value of the dataType property.
         * 
         * @param value
         *     allowed object is
         *     {@link DataType }
         *     
         */
        public void setDataType(DataType value) {
            this.dataType = value;
        }

        /**
         * Gets the value of the dateFormate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDateFormate() {
            return dateFormate;
        }

        /**
         * Sets the value of the dateFormate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDateFormate(String value) {
            this.dateFormate = value;
        }

        /**
         * Gets the value of the source property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSource() {
            return source;
        }

        /**
         * Sets the value of the source property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSource(String value) {
            this.source = value;
        }

        /**
         * Gets the value of the query property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQuery() {
            return query;
        }

        /**
         * Sets the value of the query property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQuery(String value) {
            this.query = value;
        }

        /**
         * Gets the value of the mapping property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mapping property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMapping().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Entity.Colomn.Mapping }
         * 
         * 
         */
        public List<Entity.Colomn.Mapping> getMapping() {
            if (mapping == null) {
                mapping = new ArrayList<Entity.Colomn.Mapping>();
            }
            return this.mapping;
        }

        /**
         * Gets the value of the key property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isKey() {
            return key;
        }

        /**
         * Sets the value of the key property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setKey(Boolean value) {
            this.key = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Mapping {

            @XmlElement(required = true)
            protected String key;
            @XmlElement(required = true)
            protected String value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }
        
        @Override
        public int compareTo(Colomn other) {
        	if(this.getOrder()>other.getOrder()) return 1;
        	else if(this.getOrder()<other.getOrder()) return -1;
        	return 0;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Param" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="data-type" type="{}DataType"/>
     *                   &lt;element name="date-formate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "param"
    })
    public static class Parameters {

        @XmlElement(name = "Param")
        protected List<Entity.Parameters.Param> param;

        /**
         * Gets the value of the param property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the param property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParam().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Entity.Parameters.Param }
         * 
         * 
         */
        public List<Entity.Parameters.Param> getParam() {
            if (param == null) {
                param = new ArrayList<Entity.Parameters.Param>();
            }
            return this.param;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="data-type" type="{}DataType"/>
         *         &lt;element name="date-formate" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "name",
            "value",
            "dataType",
            "dateFormate"
        })
        public static class Param {

            @XmlElement(required = true)
            protected String name;
            @XmlElement(required = true)
            protected String value;
            @XmlElement(name = "data-type", required = true)
            @XmlSchemaType(name = "string")
            protected DataType dataType;
            @XmlElement(name = "date-formate", required = true)
            protected String dateFormate;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the dataType property.
             * 
             * @return
             *     possible object is
             *     {@link DataType }
             *     
             */
            public DataType getDataType() {
                return dataType;
            }

            /**
             * Sets the value of the dataType property.
             * 
             * @param value
             *     allowed object is
             *     {@link DataType }
             *     
             */
            public void setDataType(DataType value) {
                this.dataType = value;
            }

            /**
             * Gets the value of the dateFormate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDateFormate() {
                return dateFormate;
            }

            /**
             * Sets the value of the dateFormate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDateFormate(String value) {
                this.dateFormate = value;
            }

        }

    }
    
    public Colomn getKeyColomn(){
    	if(!CommonUtil.isEmptyCollection(this.getColomn())){
    		for(Colomn c : this.getColomn()){
    			if(c.isKey()){
    				return c;
    			}
    		}
    	}
    	return null;    	
    }

}
