# Keep the names of our models so that Moshi can use them
-keepnames class click.toshi.ipaam1.data.api.response.** { *; }

# Keep SourceFile names & Line Numbers for stack traces. (Note: If we are really security concious,
# we should remove this line.
-keepattributes SourceFile,LineNumberTable
