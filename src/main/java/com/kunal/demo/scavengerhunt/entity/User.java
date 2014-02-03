package com.kunal.demo.scavengerhunt.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import com.kunal.demo.scavengerhunt.entity.Group;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @ManyToOne(optional = false)
   private Group group;

   @Column
   private String userName;

   @Column
   private String displayName;

   @Column
   private String password;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((User) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public Group getGroup()
   {
      return this.group;
   }

   public void setGroup(final Group group)
   {
      this.group = group;
   }

   public String getUserName()
   {
      return this.userName;
   }

   public void setUserName(final String userName)
   {
      this.userName = userName;
   }

   public String getDisplayName()
   {
      return this.displayName;
   }

   public void setDisplayName(final String displayName)
   {
      this.displayName = displayName;
   }

   public String getPassword()
   {
      return this.password;
   }

   public void setPassword(final String password)
   {
      this.password = password;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (userName != null && !userName.trim().isEmpty())
         result += "userName: " + userName;
      if (displayName != null && !displayName.trim().isEmpty())
         result += ", displayName: " + displayName;
      if (password != null && !password.trim().isEmpty())
         result += ", password: " + password;
      return result;
   }
}