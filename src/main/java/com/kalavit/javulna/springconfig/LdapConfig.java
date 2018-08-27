/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.springconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author peti
 */
@Configuration
@ConfigurationProperties(prefix = "javulna.ldap")
public class LdapConfig {
    private String url;
    private String binddn;
    private String bindpwd;
    private String searchbase;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBinddn() {
        return binddn;
    }

    public void setBinddn(String binddn) {
        this.binddn = binddn;
    }

    public String getBindpwd() {
        return bindpwd;
    }

    public void setBindpwd(String bindpwd) {
        this.bindpwd = bindpwd;
    }

    public String getSearchbase() {
        return searchbase;
    }

    public void setSearchbase(String searchbase) {
        this.searchbase = searchbase;
    }
    
    
}
