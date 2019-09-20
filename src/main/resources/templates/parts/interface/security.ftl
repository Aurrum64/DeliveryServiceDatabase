<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAccountActivated = user.isAccountActivated()
    isAdmin = user.isAdmin()
    isUser = user.isUser()
    isManager = user.isManager()
    isCourier = user.isCourier()
    isEnabled = user.isActive()
    >
<#else>
    <#assign
    name = "Guest"
    isAccountActivated = true
    isAdmin = false
    isUser = false
    isManager = false
    isCourier = false
    isEnabled = false
    >
</#if>