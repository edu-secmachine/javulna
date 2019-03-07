/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.services;

import com.kalavit.javulna.dto.LdapUserDto;
import com.kalavit.javulna.springconfig.LdapConfig;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author peti
 */
@Service
public class LdapService {

    @Autowired
    LdapConfig ldapConfig;

    private DirContext initContext() throws NamingException {
        Hashtable<String, String> environment = new Hashtable<String, String>();

        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, ldapConfig.getUrl());
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, ldapConfig.getBinddn());
        environment.put(Context.SECURITY_CREDENTIALS, ldapConfig.getBindpwd());

        environment.put(Context.STATE_FACTORIES, "PersonStateFactory");
        environment.put(Context.OBJECT_FACTORIES, "PersonObjectFactory");

        DirContext ctx = new InitialDirContext(environment);
        return ctx;
    }

    public LdapUserDto findUser(String uid, String password) {

        try {
            LdapUserDto ret = new LdapUserDto();
            DirContext ctx = initContext();
            String filter = "(&(uid=" + uid + ") (userPassword=" + password + "))";

            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            NamingEnumeration answer = ctx.search(ldapConfig.getSearchbase(), filter, ctls);

            SearchResult sr = (SearchResult) answer.next();
            Attributes attrs = sr.getAttributes();
            if (attrs != null) {

            }
            ret.setCommonName(getAttr(attrs, "cn"));
            ret.setObjectClass(getAttr(attrs, "objectclass"));
            ret.setIsdnNumber(getAttr(attrs, "internationaliSDNNumber"));
            ret.setMail(getAttr(attrs, "mail"));
            ret.setPhoneNumber(getAttr(attrs, "telephoneNumber"));
            ret.setUserId(getAttr(attrs, "uid"));
            ret.setSurName(getAttr(attrs, "sn"));
            return ret;
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }

    }

    private String getAttr(Attributes attrs, String attrName) throws NamingException {
        Attribute attr = attrs.get(attrName);
        if (attr != null) {
            String[] strAttrs = new String[attr.size()];
            NamingEnumeration<?> all = attr.getAll();
            int i=0;
            while(all.hasMore()){
                Object next = all.next();
                strAttrs[i]=next.toString();
                i++;
            }
            return StringUtils.arrayToCommaDelimitedString(strAttrs);
        }
        return null;
    }

}
