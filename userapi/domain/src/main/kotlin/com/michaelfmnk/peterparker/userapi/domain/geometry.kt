package com.michaelfmnk.peterparker.userapi.domain

import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point

internal fun pointOf(x: Double, y: Double): Point = GeometryFactory().createPoint(Coordinate(x, y))
internal fun pointOf(x: Int, y: Int): Point = pointOf(x.toDouble(), y.toDouble())

