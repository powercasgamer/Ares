package dev.mizule.ares.app.util;

import dev.mizule.ares.app.dependency.classloader.ClassLoaderHelper;
import dev.mizule.ares.app.dependency.classloader.SystemClassLoaderHelper;
import xyz.jpenilla.gremlin.runtime.ClasspathAppender;

import java.nio.file.Path;

public class AppClassPathAppender implements ClasspathAppender {

    private final ClassLoaderHelper classLoaderAccess;

    public AppClassPathAppender() {
        this(Util.class.getClassLoader());
    }

    public AppClassPathAppender(ClassLoader parentLoader) {
        this.classLoaderAccess = new SystemClassLoaderHelper(parentLoader);
    }

    @Override
    public void append(Path path) {
        classLoaderAccess.addToClasspath(path);
    }
}
