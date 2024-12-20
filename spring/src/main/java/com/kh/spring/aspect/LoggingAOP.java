package com.kh.spring.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/*
 * # @Aspect : 해당 클래스가 Aspect라는 것을 의미함
 * # @Component : Spring이 해당 빈을 서치할 수 있도록 선언 (빈에 등록)
 *  	// @Slf4j : 로그 등록 시 쓰는 어노테이션
 * # @EnableAspectJAutoProxy : aop를 활성화 시켜주는 선언
 */

// 내가 원하는 시점을 지정하기 위해 aop를 사용하는 것
@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy // 이거 사용할 때는 Component 어노테이션 안 써줘도 됨
// 이건 Aspect라는 걸 알려주는 거지 빈에 등록해주는 게 아님 -> Component 어노테이션도 같이 써줘야 함, 그래야 빈에 등록해줌
public class LoggingAOP {
//	로그가 필요한 곳에 덕지덕지 붙여줄 것

//	 타겟으로 하고 있는 원래 기능의 앞뒤에 붙은 추가적인 기능을 advice라고 함
	/*
	 * <시점>
	 * @Before : 대상 메소드 실행 전에 Advice(추가 기능)가 실행됨
	 * 
	 * @After : 대상 메소드 실행 후에 Advice(추가 기능)가 실행됨
	 * 
	 * @AfterReturning : 대상 메소드가 정상적으로 반환된 후에 Advice가 실행됨
	 * 
	 * @AfterThrowing : 대상 메소드가 예외를 던진 후에 Advice가 실행됨
	 * 
	 * @Around : 대상 메소드를 감싸서 메소드 호출 전후에 Advice가 실행됨
	 * 		// before와 after를 합쳐 놓은 것
	 * 
	 * 
	 * 
	 * <대상>
	 * target : 특정 인터페이스와 그 자식 클래스
	 * within : 특정 패키지 또는 클래스
	 * execution : 표현식으로 형태 지정
	 *   // 표현식 : 아직 정해지지 않았다, 형식만 지정해두는 것?
	 * 
	 */
	
	// 리턴 타입, 파라미터, 예외
	// @Pointcut : 기능을 사용할 지점을 정의하는 것
	// com.kh.spring 패키지 하위 패키지 중 controller 내에 있는 모든 클래스의 모든 메소드
	@Pointcut("execution(*com.kh.spring..controller.*.*(..))") // aop에서 많이 쓰는 어노테이션
//	.. : spring 안의 어떤 폴더일지 모름
//	controller 안의 모든 폴더 안의 모든 객체
//	표현식은 chatgpt의 도움을 받는 게 좋음
	
//	메소드가 실행되기 이전에 before after proxy가 이 메소드들을 감싸줌, before after가 무조건 실행되게 만듦
	// cut 메소드가 실행되는 지점 이전에 before() 메소드를 실행
	// JoinPoint는 프로그램의 실행 중 특정 지점을 나타내며,
	// 메소드 실행이 가장 일반적인 JoinPoint1
	@Before("target(com.kh.spring.board.controller.BoardController)") // 이 메소드에 하겠다고 join point를 지정해줄 수 있음, 이걸 할 때 하겠다
//	보통 한 메소드에 하나씩 정해주지는 않음
	public void before(JoinPoint joinPoint) {
		// 실행 되는 메소드의 이름을 가져오기
//		이건 java
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method method = methodSignature.getMethod();
//		java.lang.reflect 것
		
		// 메소드에 들어가는 매개변수 배열을 읽어옴
		Object[] args = joinPoint.getArgs();
//		joinPoint가 어느 메소드로 갈지에 대한 정보를 가지고 있음, 따라서 전달되는 데이터도 가지고 있을 것
		
		log.info("===================START===================");
		log.info("----------------API Controller---------------");
		log.info("Information    :    " + methodSignature);
		log.info("Method Name    :    " + method);
		log.info("Parameter    :    " + Arrays.toString(args));
	}
	
	@AfterReturning(value="cut()", returning="obj")
	public void afterReturn(JoinPoint joinPoint, Object obj) {
		log.info("===================END===================");
		log.info("Object    :    " + obj);
	}
	
	// API 시간 측정
	@Around("cut()")
	public Object displayLogInfo(ProceedingJoinPoint pJoinPoint) throws Throwable {
//		ProceedingJoinPoint : 원래 해야 하는 기능이 추가된 JoinPoint
		// 이 메소드가? 끝나기까지 얼마나 걸리는지 체크하기
		long start = System.currentTimeMillis(); // 현재 시간을 찍어주는 메소드
		
//		before에서 잠깐 걸린 것이므로 다시 원래 기능을 실행 시켜줄 것
		
		Object result = pJoinPoint.proceed(); // 원래 해야 하는 기능을 실행해줌
		
		long end = System.currentTimeMillis();
		
		long pTime = end - start;
		
		log.info("-------------------------------");
		log.info("Information    :    " + pJoinPoint.getSignature());
		log.info("Processing Time    :    " + pTime + "m/s");
		
		return result;
//		얘를 실행 하는 스프링한테 리턴 값을 돌려주는 것
	}
	
//	loggin 코드가 컨트롤러마다 다 붙는다? 다 한 줄임, 코드마다 있으니까 중복됨
//	이런 중복되는 코드는 보일러 플레이트, 중복되는 건 다 한데 모아서 빼줘야 함
//	=> aop, 관점 지향 프로그래밍으로 한데 모아두겠다
//	proxy 이용, 원래 실행해야 하는 기능에 proxy를 이용해서 감싸주는 것
//	이걸 통해 before after around 방식으로 기능 실행 전에 선행 혹은 후행할 메소드를 따로 넣어주는 것
//	여기서 이용되는 이 aop 실행 지점이 Pointcut, 이 실행되는 추가 기능들이 Advice
	
	
}
