package ru.arkanoid.proto.lib;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: transaction.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TransactionServiceGrpc {

  private TransactionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "TransactionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.arkanoid.proto.lib.TransactionRequest,
      ru.arkanoid.proto.lib.TransactionResponse> getBuyGameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "buyGame",
      requestType = ru.arkanoid.proto.lib.TransactionRequest.class,
      responseType = ru.arkanoid.proto.lib.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.arkanoid.proto.lib.TransactionRequest,
      ru.arkanoid.proto.lib.TransactionResponse> getBuyGameMethod() {
    io.grpc.MethodDescriptor<ru.arkanoid.proto.lib.TransactionRequest, ru.arkanoid.proto.lib.TransactionResponse> getBuyGameMethod;
    if ((getBuyGameMethod = TransactionServiceGrpc.getBuyGameMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getBuyGameMethod = TransactionServiceGrpc.getBuyGameMethod) == null) {
          TransactionServiceGrpc.getBuyGameMethod = getBuyGameMethod =
              io.grpc.MethodDescriptor.<ru.arkanoid.proto.lib.TransactionRequest, ru.arkanoid.proto.lib.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "buyGame"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.arkanoid.proto.lib.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.arkanoid.proto.lib.TransactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("buyGame"))
              .build();
        }
      }
    }
    return getBuyGameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.arkanoid.proto.lib.TransactionRequest,
      ru.arkanoid.proto.lib.TransactionResponse> getPayOutGameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "payOutGame",
      requestType = ru.arkanoid.proto.lib.TransactionRequest.class,
      responseType = ru.arkanoid.proto.lib.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.arkanoid.proto.lib.TransactionRequest,
      ru.arkanoid.proto.lib.TransactionResponse> getPayOutGameMethod() {
    io.grpc.MethodDescriptor<ru.arkanoid.proto.lib.TransactionRequest, ru.arkanoid.proto.lib.TransactionResponse> getPayOutGameMethod;
    if ((getPayOutGameMethod = TransactionServiceGrpc.getPayOutGameMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getPayOutGameMethod = TransactionServiceGrpc.getPayOutGameMethod) == null) {
          TransactionServiceGrpc.getPayOutGameMethod = getPayOutGameMethod =
              io.grpc.MethodDescriptor.<ru.arkanoid.proto.lib.TransactionRequest, ru.arkanoid.proto.lib.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "payOutGame"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.arkanoid.proto.lib.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.arkanoid.proto.lib.TransactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("payOutGame"))
              .build();
        }
      }
    }
    return getPayOutGameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransactionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceStub>() {
        @java.lang.Override
        public TransactionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceStub(channel, callOptions);
        }
      };
    return TransactionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransactionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceBlockingStub>() {
        @java.lang.Override
        public TransactionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceBlockingStub(channel, callOptions);
        }
      };
    return TransactionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransactionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceFutureStub>() {
        @java.lang.Override
        public TransactionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceFutureStub(channel, callOptions);
        }
      };
    return TransactionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void buyGame(ru.arkanoid.proto.lib.TransactionRequest request,
        io.grpc.stub.StreamObserver<ru.arkanoid.proto.lib.TransactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBuyGameMethod(), responseObserver);
    }

    /**
     */
    default void payOutGame(ru.arkanoid.proto.lib.TransactionRequest request,
        io.grpc.stub.StreamObserver<ru.arkanoid.proto.lib.TransactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPayOutGameMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TransactionService.
   */
  public static abstract class TransactionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TransactionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TransactionService.
   */
  public static final class TransactionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TransactionServiceStub> {
    private TransactionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceStub(channel, callOptions);
    }

    /**
     */
    public void buyGame(ru.arkanoid.proto.lib.TransactionRequest request,
        io.grpc.stub.StreamObserver<ru.arkanoid.proto.lib.TransactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBuyGameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void payOutGame(ru.arkanoid.proto.lib.TransactionRequest request,
        io.grpc.stub.StreamObserver<ru.arkanoid.proto.lib.TransactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPayOutGameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TransactionService.
   */
  public static final class TransactionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TransactionServiceBlockingStub> {
    private TransactionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ru.arkanoid.proto.lib.TransactionResponse buyGame(ru.arkanoid.proto.lib.TransactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBuyGameMethod(), getCallOptions(), request);
    }

    /**
     */
    public ru.arkanoid.proto.lib.TransactionResponse payOutGame(ru.arkanoid.proto.lib.TransactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPayOutGameMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TransactionService.
   */
  public static final class TransactionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TransactionServiceFutureStub> {
    private TransactionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.arkanoid.proto.lib.TransactionResponse> buyGame(
        ru.arkanoid.proto.lib.TransactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBuyGameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.arkanoid.proto.lib.TransactionResponse> payOutGame(
        ru.arkanoid.proto.lib.TransactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPayOutGameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BUY_GAME = 0;
  private static final int METHODID_PAY_OUT_GAME = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BUY_GAME:
          serviceImpl.buyGame((ru.arkanoid.proto.lib.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<ru.arkanoid.proto.lib.TransactionResponse>) responseObserver);
          break;
        case METHODID_PAY_OUT_GAME:
          serviceImpl.payOutGame((ru.arkanoid.proto.lib.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<ru.arkanoid.proto.lib.TransactionResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getBuyGameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.arkanoid.proto.lib.TransactionRequest,
              ru.arkanoid.proto.lib.TransactionResponse>(
                service, METHODID_BUY_GAME)))
        .addMethod(
          getPayOutGameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.arkanoid.proto.lib.TransactionRequest,
              ru.arkanoid.proto.lib.TransactionResponse>(
                service, METHODID_PAY_OUT_GAME)))
        .build();
  }

  private static abstract class TransactionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransactionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.arkanoid.proto.lib.Transaction.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TransactionService");
    }
  }

  private static final class TransactionServiceFileDescriptorSupplier
      extends TransactionServiceBaseDescriptorSupplier {
    TransactionServiceFileDescriptorSupplier() {}
  }

  private static final class TransactionServiceMethodDescriptorSupplier
      extends TransactionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TransactionServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TransactionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransactionServiceFileDescriptorSupplier())
              .addMethod(getBuyGameMethod())
              .addMethod(getPayOutGameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
