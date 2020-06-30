package sttp

package object client extends SttpApi {
  type Identity[X] = X
  type Empty[X] = None.type

  /**
    * A [[RequestT]] without the method & uri specified (which cannot yet be sent).
    */
  type PartialRequest[T, -R] = RequestT[Empty, T, R]

  /**
    * A [[RequestT]] with the method & uri specified. Such a request can be sent.
    */
  type Request[T, -R] = RequestT[Identity, T, R]

  type ResponseError[DE] = ResponseErrorTyped[String, DE]

  /**
    * Provide an implicit value of this type to serialize arbitrary classes into a request body.
    * Backends might also provide special logic for serializer instances which they define (e.g. to handle streaming).
    */
  type BodySerializer[B] = B => BasicRequestBody

  type RetryWhen = (Request[_, _], Either[Throwable, Response[_]]) => Boolean
}
