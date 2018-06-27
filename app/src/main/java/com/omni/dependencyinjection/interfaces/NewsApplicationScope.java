package com.omni.dependencyinjection.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


@Scope
@Retention(RetentionPolicy.CLASS)
public @interface NewsApplicationScope {

}
