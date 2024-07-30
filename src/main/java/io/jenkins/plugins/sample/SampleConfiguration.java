package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.ExtensionList;
import hudson.util.FormValidation;
import hudson.util.Secret;
import jenkins.model.GlobalConfiguration;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

/**
 * Example of Jenkins global configuration.
 */
@Extension
public class SampleConfiguration extends GlobalConfiguration {

    /** @return the singleton instance */
    public static SampleConfiguration get() {
        return ExtensionList.lookupSingleton(SampleConfiguration.class);
    }

    private String label;
    private String description;
    private String url;
    private String userName;
    private Secret password;
    private boolean optionalBlock;

    public boolean isOptionalBlock() {
        return optionalBlock;
    }

    @DataBoundSetter
    public void setOptionalBlock(boolean optionalBlock) {
        this.optionalBlock = optionalBlock;
        save();
    }

    public Secret getPassword() {
        return password;
    }

    @DataBoundSetter
    public void setPassword(Secret password) {
        this.password = password;
        save();
    }



    public String getUserName() {
        return userName;
    }

    @DataBoundSetter
    public void setUserName(String userName) {
        this.userName = userName;
        save();
    }



    public String getUrl() {
        return url;
    }

    @DataBoundSetter
    public void setUrl(String url) {
        this.url = url;
        save();
    }

    public String getDescription() {
        return description;
    }

   @DataBoundSetter
    public void setDescription(String description) {
        this.description = description;
        save();
    }

    public SampleConfiguration() {
        // When Jenkins is restarted, load any saved configuration from disk.
        load();
    }

    /** @return the currently configured label, if any */
    public String getLabel() {
        return label;
    }

    /**
     * Together with {@link #getLabel}, binds to entry in {@code config.jelly}.
     * @param label the new value of this field
     */
    @DataBoundSetter
    public void setLabel(String label) {
        this.label = label;
        save();
    }

    public FormValidation doCheckLabel(@QueryParameter String value) {
        if (StringUtils.isEmpty(value)) {
            return FormValidation.warning("Please specify a label.");
        }if (!value.matches("[a-zA-Z ]+")) {
            return FormValidation.warning("Name can only contain letters and spaces.");
        }
        return FormValidation.ok();
    }
    public FormValidation doCheckDescription(@QueryParameter String value) {
        if (StringUtils.isEmpty(value)) {
            return FormValidation.warning("Please specify a description.");
        }
        return FormValidation.ok();
    }
    public FormValidation doCheckUserName(@QueryParameter String value) {
        if(StringUtils.isEmpty(value)) {
            return FormValidation.warning("Please specify username");
        } if (!value.matches("[a-zA-Z]+")) {
            return FormValidation.warning("UserName can only contain letters.");
        }
        return FormValidation.ok();
    }

}
