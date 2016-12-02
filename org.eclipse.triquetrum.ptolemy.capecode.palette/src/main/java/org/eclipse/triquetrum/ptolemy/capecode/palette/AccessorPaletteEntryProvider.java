package org.eclipse.triquetrum.ptolemy.capecode.palette;

import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.CLASS;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.DISPLAY_NAME;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.ICON;
import static org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement.TYPE;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteConfigurationElement;
import org.eclipse.triquetrum.workflow.editor.palette.spi.PaletteEntryProvider;
import org.terraswarm.accessor.AccessorLibrary;

import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

public class AccessorPaletteEntryProvider implements PaletteEntryProvider {

  @Override
  public IConfigurationElement[] getPaletteEntries() {
    try {
      AccessorLibrary accessorLibrary = buildAccessorLibrary();
      List<IConfigurationElement> results = new ArrayList<>();
      for (Entity<?> accessor : (List<Entity<?>>) accessorLibrary.entityList()) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put(CLASS, accessor.getClass().getName());
        attributes.put(DISPLAY_NAME, accessor.getDisplayName());
        attributes.put(ICON, "icons/time_obj.gif");
        attributes.put(TYPE, "Actor");
        PaletteConfigurationElement pce = new PaletteConfigurationElement("entry", "org.eclipse.triquetrum.ptolemy.capecode.palette", attributes );
        results.add(pce);
      }
      return results.toArray(new IConfigurationElement[0]);
    } catch (IllegalActionException | NameDuplicationException | MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new IConfigurationElement[] {};
    }
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
