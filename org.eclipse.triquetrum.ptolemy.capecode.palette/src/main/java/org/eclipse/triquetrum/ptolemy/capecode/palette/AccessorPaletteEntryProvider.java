package org.eclipse.triquetrum.ptolemy.capecode.palette;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.BufferUnderflowException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement;
import org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteEntryProvider;
import org.osgi.framework.Bundle;
import org.terraswarm.accessor.AccessorLibrary;

import ptolemy.actor.lib.CurrentTime;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.*;

public class AccessorPaletteEntryProvider implements PaletteEntryProvider {

  @Override
  public IConfigurationElement[] getPaletteEntries() {
    try {
      AccessorLibrary accessorLibrary = buildAccessorLibrary();
    } catch (IllegalActionException | NameDuplicationException | MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.err.println("I was here!");
    Map<String, String> attributes = new HashMap<>();
    attributes.put(CLASS, CurrentTime.class.getName());
    attributes.put(DISPLAY_NAME, "Current Time");
    attributes.put(ICON, "icons/time_obj.gif");
    attributes.put(TYPE, "Actor");
    PaletteConfigurationElement pce = new PaletteConfigurationElement("entry", "org.eclipse.triquetrum.ptolemy.capecode.palette", attributes );
    return new IConfigurationElement[] {pce};
  }

  private AccessorLibrary buildAccessorLibrary() throws IllegalActionException, NameDuplicationException, MalformedURLException {
    CompositeEntity ce = new CompositeEntity();
    ce.setName("CapeCode configuration");
    AccessorLibrary accessorLibrary = new AccessorLibrary(ce, "Accessors");
    accessorLibrary.configure(null, URI.createPlatformPluginURI("org.eclipse.triquetrum.ptolemy.capecode.palette",true).toString() + "/src/main/resources", null);
    accessorLibrary.populate();
    return accessorLibrary;
  }

}
