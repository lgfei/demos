package com.lgfei.demo.ssh.pojo;

import java.io.Serializable;

/**
 * @author lgfei
 *
 */
public class Base implements Serializable
{
    
    private static final long serialVersionUID = 1773347084912974856L;
    
    private Long id;
    
    private Integer createdBy;
    
    private String createdDate;
    
    private Integer lastUpdatedBy;
    
    private String lastUpdatedDate;
    
    private Integer enableFlag;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Integer getCreatedBy()
    {
        return createdBy;
    }
    
    public void setCreatedBy(Integer createdBy)
    {
        this.createdBy = createdBy;
    }
    
    public String getCreatedDate()
    {
        return createdDate;
    }
    
    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }
    
    public Integer getLastUpdatedBy()
    {
        return lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(Integer lastUpdatedBy)
    {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    public String getLastUpdatedDate()
    {
        return lastUpdatedDate;
    }
    
    public void setLastUpdatedDate(String lastUpdatedDate)
    {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getEnableFlag()
    {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag)
    {
        this.enableFlag = enableFlag;
    }
    
}
