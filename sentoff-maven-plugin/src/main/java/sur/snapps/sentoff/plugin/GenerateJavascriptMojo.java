package sur.snapps.sentoff.plugin;

import io.swagger.jaxrs.listing.ApiListingResource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @author sur
 * @since 16/04/2016
 */
@Mojo(name = "generate-javascript")
public class GenerateJavascriptMojo extends AbstractMojo {


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        ApiListingResource apiListingResource = new ApiListingResource();
        apiListingResource.getListingJson(null, null, null, null);
    }
}
