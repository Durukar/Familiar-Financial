const getUrl = () => {
  const url = new URL(window.location.href)

  return `${url.protocol}//${url.hostname}:${url.port}`
}

export {getUrl}