rootProject.name = "boxmigrator"

// Box versions
sequenceOf(
    "v4" // v3 to v4
).forEach {
    include(it)
    project(":$it").projectDir = file(it)
}
