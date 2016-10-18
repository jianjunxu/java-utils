在Java中，有很多比较实用的类库，他们通常都定义了一系列具有常见功能的方法。本文总结了最常用的Java中的实用类以及他们的最常用的方法。无论是类和类中方法都是按照流行程度来排序的。

本文中列出来的类及方法都是经过大量实践的常用类库及方法，我们可以直接拿过来用。当然，这些方法实现的功能我们自己都能实现，但是既然已经有很成熟的方法可以供我们使用了，那么就无需自己定义了。很多类和方法通过他们的名字其实可以理解出具体是做什么的。每个方法都有一个链接，可以查看他们在开源代码中具体是如何使用的。

以下列表是通过分析50K的开源项目得出来的。

1. org.apache.commons.io.IOUtils
    closeQuietly ( )
    toString ( )
    copy ( )
    toByteArray ( )
    write ( )
    toInputStream ( )
    readLines ( )
    copyLarge ( )
    lineIterator ( )
    readFully ( )

2. org.apache.commons.io.FileUtils
    deleteDirectory ( )
    readFileToString ( )
    deleteQuietly ( )
    copyFile ( )
    writeStringToFile ( )
    forceMkdir ( )
    write ( )
    listFiles ( )
    copyDirectory ( )
    forceDelete ( )

3. org.apache.commons.lang.StringUtils
    isBlank ( )
    isNotBlank ( )
    isEmpty ( )
    isNotEmpty ( )
    equals ( )
    join ( )
    split ( )
    EMPTY
    trimToNull ( )
    replace ( )

4. org.apache.http.util.EntityUtils
    toString ( )
    consume ( )
    toByteArray ( )
    consumeQuietly ( )
    getContentCharSet ( )

5. org.apache.commons.lang3.StringUtils
    isBlank ( )
    isNotBlank ( )
    isEmpty ( )
    isNotEmpty ( )
    join ( )
    equals ( )
    split ( )
    EMPTY
    replace ( )
    capitalize ( )

6. org.apache.commons.io.FilenameUtils
    getExtension ( )
    getBaseName ( )
    getName ( )
    concat ( )
    removeExtension ( )
    normalize ( )
    wildcardMatch ( )
    separatorsToUnix ( )
    getFullPath ( )
    isExtension ( )

7. org.springframework.util.StringUtils
    hasText ( )
    hasLength ( )
    isEmpty ( )
    commaDelimitedListToStringArray ( )
    collectionToDelimitedString ( )
    replace ( )
    delimitedListToStringArray ( )
    uncapitalize ( )
    collectionToCommaDelimitedString ( )
    tokenizeToStringArray ( )

8. org.apache.commons.lang.ArrayUtils
    contains ( )
    addAll ( )
    clone ( )
    isEmpty ( )
    add ( )
    EMPTY_BYTE_ARRAY
    subarray ( )
    indexOf ( )
    isEquals ( )
    toObject ( )

9. org.apache.commons.lang.StringEscapeUtils
    escapeHtml ( )
    unescapeHtml ( )
    escapeXml ( )
    escapeSql ( )
    unescapeJava ( )
    escapeJava ( )
    escapeJavaScript ( )
    unescapeXml ( )
    unescapeJavaScript ( )

10. org.apache.http.client.utils.URLEncodedUtils
    format ( )
    parse ( )

11. org.apache.commons.codec.digest.DigestUtils
    md5Hex ( )
    shaHex ( )
    sha256Hex ( )
    sha1Hex ( )
    sha ( )
    md5 ( )
    sha512Hex ( )
    sha1 ( )

12. org.apache.commons.collections.CollectionUtils
    isEmpty ( )
    isNotEmpty ( )
    select ( )
    transform ( )
    filter ( )
    find ( )
    collect ( )
    forAllDo ( )
    addAll ( )
    isEqualCollection ( )

13. org.apache.commons.lang3.ArrayUtils
    contains ( )
    isEmpty ( )
    isNotEmpty ( )
    add ( )
    clone ( )
    addAll ( )
    subarray ( )
    indexOf ( )
    EMPTY_OBJECT_ARRAY
    EMPTY_STRING_ARRAY

14. org.apache.commons.beanutils.PropertyUtils
    getProperty ( )
    setProperty ( )
    getPropertyDescriptors ( )
    isReadable ( )
    copyProperties ( )
    getPropertyDescriptor ( )
    getSimpleProperty ( )
    isWriteable ( )
    setSimpleProperty ( )
    getPropertyType ( )

15. org.apache.commons.lang3.StringEscapeUtils
    unescapeHtml4 ( )
    escapeHtml4 ( )
    escapeXml ( )
    unescapeXml ( )
    escapeJava ( )
    escapeEcmaScript ( )
    unescapeJava ( )
    escapeJson ( )
    escapeXml10 ( )

16. org.apache.commons.beanutils.BeanUtils
    copyProperties ( )
    getProperty ( )
    setProperty ( )
    describe ( )
    populate ( )
    copyProperty ( )
    cloneBean ( )