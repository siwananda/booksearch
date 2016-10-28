package modules

import bootstrap.BootstrapBooks
import com.google.inject.AbstractModule

/**
  * Bootstrapper module. Each bootstrappers can be registered here.
  * Remove this module from application.conf to stop bootstrapper from running
  */
class BootstrapperModule extends AbstractModule {
  protected def configure(): Unit = {
    bind(classOf[BootstrapBooks]).asEagerSingleton()
  }
}
